//
// Algoritmos e Estruturas de Dados --- 2024/2025
//
// J Madeira, J M Rodrigues, Nov 2023, Nov 2024
//

// Complete the functions (marked by ...)
// so that they pass all tests.

#include "PersonSet.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "BSTree.h"

// NOTE THAT:
// - Field capacity was eliminated.
// - Field size was eliminated, because size==BSTreeGetNumberOfNodes(...).

// Definition of the structure
struct _PersonSet_ {
  BSTree *persons;  // points to a BSTree of Person pointers
};

// Comparison function to be used in generic BSTree.
// Comparison is based on Person ID
static int cmpP(const void *a, const void *b) {
  Person *p1 = (Person *)a;
  Person *p2 = (Person *)b;
  int d = p1->id - p2->id;
  return (d > 0) - (d < 0);
}

// Print function to be used in generic BSTree.
static void printP(void *p) { PersonPrintf((Person *)p, ""); }

// Create a PersonSet.
PersonSet *PersonSetCreate() {
  PersonSet* set = malloc(sizeof(struct _PersonSet_));
  if(set==NULL) return NULL;
  set->persons=BSTreeCreate(cmpP, printP);
  if((set->persons)==NULL) return NULL;

  return set;
}

// Destroy PersonSet *pps
void PersonSetDestroy(PersonSet **pps) {
  assert(*pps != NULL);
  BSTreeDestroy(&(*pps)->persons);
  (*pps)->persons=NULL;
  free(*pps);
  pps=NULL;
}

int PersonSetSize(const PersonSet *ps) {
  return BSTreeGetNumberOfNodes(ps->persons);
}

int PersonSetIsEmpty(const PersonSet *ps) { return BSTreeIsEmpty(ps->persons); }

// Print function to use in PersonSetPrint
static void printlnP(void *p) { PersonPrintf((Person *)p, ";\n"); }

void PersonSetPrint(const PersonSet *ps) {
  printf("{\n");
  BSTreeTraverseINOrder(ps->persons, printlnP);
  printf("}(size=%d)\n", PersonSetSize(ps));
}

// Find node in BSTree ps->persons of person with given id.
// Return a pointer to the stored Person instance.
// Or NULL, if it does not belong to the BSTree.
// (INTERNAL function.)
static Person *search(const PersonSet *ps, int id) {
  Person dummyPerson;
  dummyPerson.id = id;
  return BSTreeSearch(ps->persons, &dummyPerson);
}

// Add person *p to *ps.
// Do nothing if *ps already contains a person with the same id.
void PersonSetAdd(PersonSet *ps, Person *p) {
  BSTreeAdd(ps->persons, p);
}

// Pop one person out of *ps.
Person *PersonSetPop(PersonSet *ps) {
  assert(!PersonSetIsEmpty(ps));

  Person *toRemove = QueueDequeue(BSTreeGetItems(ps->persons));
  if(toRemove==NULL) return NULL;
  BSTreeRemove(ps->persons,toRemove);
  return toRemove;
}

// Remove the person with given id from *ps, and return it.
// If no such person is found, return NULL and leave set untouched.
Person *PersonSetRemove(PersonSet *ps, int id) {
  
  Person *found = search(ps, id);
  if(found==NULL) return NULL;

  BSTreeRemove(ps->persons, found);
  return found;
}

// Get the person with given id of *ps.
// return NULL if it is not in the set.
Person *PersonSetGet(const PersonSet *ps, int id) {

  Person *found = search(ps, id);
  if(found==NULL) return NULL;
  
  return found;
}

// Return true (!= 0) if set contains person with given id, false otherwise.
int PersonSetContains(const PersonSet *ps, int id) {
  return search(ps, id) != NULL;
}

// Return a NEW PersonSet with the union of *ps1 and *ps2.
// NOTE: memory is allocated.  Client must call PersonSetDestroy!
PersonSet *PersonSetUnion(const PersonSet *ps1, const PersonSet *ps2) {
  PersonSet *ps = PersonSetCreate();
  
  Queue* queue1 = BSTreeGetItems(ps1->persons);
  Queue* queue2 = BSTreeGetItems(ps2->persons);

  while(!QueueIsEmpty(queue1)){
    BSTreeAdd(ps->persons, QueueDequeue(queue1));
  }
  while(!QueueIsEmpty(queue2)){
    Person* personToAdd= QueueDequeue(queue2);
    if(!BSTreeContains(ps->persons, personToAdd)) BSTreeAdd(ps->persons, personToAdd);
  }

  QueueDestroy(&queue1);
  QueueDestroy(&queue2);

  return ps;
}

// Return a NEW PersonSet with the intersection of *ps1 and *ps2.
// NOTE: memory is allocated.  Client must call PersonSetDestroy!
PersonSet *PersonSetIntersection(const PersonSet *ps1, const PersonSet *ps2) {
  PersonSet *ps = PersonSetCreate();
  
  Queue* queue1 = BSTreeGetItems(ps1->persons);

  while(!QueueIsEmpty(queue1)){
    Person* personToAdd= QueueDequeue(queue1);
    if(PersonSetContains(ps2, personToAdd->id)) BSTreeAdd(ps->persons, personToAdd);
  }
  QueueDestroy(&queue1);


  return ps;
}

// Return a NEW PersonSet with the set difference of *ps1 and *ps2.
// NOTE: memory is allocated.  Client must call PersonSetDestroy!
PersonSet *PersonSetDifference(const PersonSet *ps1, const PersonSet *ps2) {
  PersonSet *ps = PersonSetCreate();
  
  Queue* queue1 = BSTreeGetItems(ps1->persons);

  while(!QueueIsEmpty(queue1)){
    Person* personToAdd= QueueDequeue(queue1);
    if(!PersonSetContains(ps2, personToAdd->id)) BSTreeAdd(ps->persons, personToAdd);
  }
  QueueDestroy(&queue1);


  return ps;
}

// Return true iff *ps1 is a subset of *ps2.
int PersonSetIsSubset(const PersonSet *ps1, const PersonSet *ps2) {

  Queue* queue1 = BSTreeGetItems(ps1->persons);

  while(!QueueIsEmpty(queue1)){
    Person* personToSearch= QueueDequeue(queue1);
    if(!PersonSetContains(ps2, personToSearch->id)) return 0;
  }

  QueueDestroy(&queue1);

  return 1;
}

// Return true if the two sets contain exactly the same elements.
int PersonSetEquals(const PersonSet *ps1, const PersonSet *ps2) {
  return PersonSetSize(ps1) == PersonSetSize(ps2) &&
         PersonSetIsSubset(ps1, ps2);
}
