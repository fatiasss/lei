//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// Joaquim Madeira, Nov 2023, Nov 2024
//

// Complete the functions (marked by ...)
// so that they pass all tests.

#include "PersonSet.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definition of the structure
struct _PersonSet_ {
  int capacity;    // the current capacity of the array
  int size;        // the number of elements currently stored
  Person **array;  // points to an array of pointers to persons
};

#define INITIAL_CAPACITY 4

// You may add auxiliary definitions and declarations here, if you need to.

// Create a PersonSet.
PersonSet *PersonSetCreate() {
  // You must allocate space for the struct and for the array.
  // The array should be created with INITIAL_CAPACITY elements.
  // (The array will be reallocated if necessary, when elements are appended.)

  PersonSet* pSet= malloc(sizeof(PersonSet));
  if(pSet==NULL){
    return NULL;
  }
  Person **pArray= malloc(sizeof(Person*)*INITIAL_CAPACITY);

  if(pArray==NULL){
    return NULL;
  }

  pSet->capacity=INITIAL_CAPACITY;
  pSet->size=0;
  pSet->array=pArray;


  return pSet;
}

// Destroy PersonSet *pps
void PersonSetDestroy(PersonSet **pps) {
  assert(*pps != NULL);
  free((*pps)->array);
  free(*pps);
  pps=NULL;
}

int PersonSetSize(const PersonSet *ps) { return ps->size; }

int PersonSetIsEmpty(const PersonSet *ps) { return ps->size == 0; }

void PersonSetPrint(const PersonSet *ps) {
  printf("{\n");
  for (int i = 0; i < ps->size; i++) {
    Person *p = ps->array[i];
    PersonPrintf(p, ";\n");
  }
  printf("}(size=%d, capacity=%d)\n", ps->size, ps->capacity);
}

// Find index in ps->array of person with given id.
// (INTERNAL function.)
static int search(const PersonSet *ps, int id) {

  for(int i=0; i<PersonSetSize(ps); i++){
    int currentPersonID= ((ps)->array[i])->id;
    if(currentPersonID==id) return i;
  };

  return -1;
}

// Append person *p to *ps, without verifying presence.
// Use only when sure that *p is not contained in *ps!
// (INTERNAL function.)
static void append(PersonSet *ps, Person *p) {
  // MODIFY the function so that if the array is full,
  // it uses realloc to double the array capacity!

  if(ps->capacity==ps->size){
    ps->array = realloc(ps->array, sizeof(Person*) * (ps->size)*2);
    ps->capacity *= 2; 
  }
  ps->array[ps->size] = p;
  ps->size++;
}

// Add person *p to *ps.
// Do nothing if *ps already contains a person with the same id.
void PersonSetAdd(PersonSet *ps, Person *p) {
  // You may call the append function here!
  if(search(ps, p->id)==-1){
    append(ps, p);
  }
}

// Pop one person out of *ps.
Person *PersonSetPop(PersonSet *ps) {
  assert(!PersonSetIsEmpty(ps));
  // It is easiest to pop and return the person in the last position!
  ps->size--;
  Person* lastPerson=ps->array[ps->size];
  ps->array[ps->size]=NULL;
  return lastPerson;
}

// Remove the person with given id from *ps, and return it.
// If no such person is found, return NULL and leave set untouched.
Person *PersonSetRemove(PersonSet *ps, int id) {

  int foundIndex= search(ps, id);
  if(foundIndex==-1){
    return NULL;
  }
  Person* removedPerson= PersonSetGet(ps, id);

  ps->array[foundIndex]=NULL;

  for(int i=foundIndex+1; i<ps->size; i++){
    ps->array[i-1]=ps->array[i];
  }
  ps->size--;
  ps->array[ps->size]=NULL;

  return removedPerson;

}

// Get the person with given id of *ps.
// return NULL if it is not in the set.
Person *PersonSetGet(const PersonSet *ps, int id) {
  // You may call search here!
  int foundIndex= search(ps, id);
  if(foundIndex==-1){
    return NULL;
  }
  Person *foundPerson= ps->array [foundIndex];

  return foundPerson;
}

// Return true (!= 0) if set contains person wiht given id, false otherwise.
int PersonSetContains(const PersonSet *ps, int id) {
  return search(ps, id) >= 0;
}

// Return a NEW PersonSet with the union of *ps1 and *ps2.
// Return NULL if allocation fails.
// NOTE: memory is allocated.  Client must call PersonSetDestroy!
PersonSet *PersonSetUnion(const PersonSet *ps1, const PersonSet *ps2) {
  PersonSet *ps = PersonSetCreate();
  int ps1Size=ps1->size;
  int ps2Size=ps2->size;
  int biggestSetSize= ps1Size > ps2Size ? ps1Size : ps2Size;

  for(int i=0; i<biggestSetSize; i++){
    if(i<ps1Size){
      PersonSetAdd(ps, ps1->array[i]);
    }
    if(i<ps2Size){
      PersonSetAdd(ps, ps2->array[i]);
    }
    
  }

  return ps;
}

// Return a NEW PersonSet with the intersection of *ps1 and *ps2.
// Return NULL if allocation fails.
// NOTE: memory is allocated.  Client must call PersonSetDestroy!
PersonSet *PersonSetIntersection(const PersonSet *ps1, const PersonSet *ps2) {
  PersonSet *ps = PersonSetCreate();

  int ps1Size=ps1->size;

  for(int i=0; i<ps1Size; i++){
    Person *personToAdd= ps1->array[i];
    if(search(ps2, (*personToAdd).id)!=-1){
      PersonSetAdd(ps, personToAdd);
    }
  }
  return ps;

}

// Return a NEW PersonSet with the set difference of *ps1 and *ps2.
// Return NULL if allocation fails.
// NOTE: memory is allocated.  Client must call PersonSetDestroy!
PersonSet *PersonSetDifference(const PersonSet *ps1, const PersonSet *ps2) {
  PersonSet *ps = PersonSetCreate();

  int ps1Size=ps1->size;

  for(int i=0; i<ps1Size; i++){
    Person *personToAdd= ps1->array[i];
    if(search(ps2, (*personToAdd).id)==-1){
      PersonSetAdd(ps, personToAdd);
    }
  }
  return ps;

}

// Return true iff *ps1 is a subset of *ps2.
int PersonSetIsSubset(const PersonSet *ps1, const PersonSet *ps2) {
  int ps2Size=ps2->size;
  
  for(int i=0; i<ps2Size; i++){
    Person *personToCheck=ps2->array[i];
    if(search(ps1, (*personToCheck).id) == -1){
      return 0;
    }
  }

  return 1;
}

// Return true if the two sets contain exactly the same elements.
int PersonSetEquals(const PersonSet *ps1, const PersonSet *ps2) {
  // You may call PersonSetIsSubset here!
  return ps1->size==ps2->size && PersonSetIsSubset(ps1, ps2) && PersonSetIsSubset(ps2, ps1);
}
