.data
lista:      .asciiz "123456789"    
prompt1:    .asciiz "Length: "
newline:    .asciiz "\n"

.text
.globl main

main:
     la $a0, lista
     jal strlen
     la $a0, prompt1
     li $v0, 4
     syscall
     move $a0, $v1
     li $v0, 1
     syscall
     j exit
     
      





strlen:
	li $t0, 0 #i
	li $v1, 0 #n counter
	
	
while:  lb $t2, 0($a0)
        beq $t2, '\0', stop
        addi $v1, $v1, 1
        addi $a0, $a0, 1 
        addi $t0, $t0, 1
        j while
        
stop: move $a1, $t1
      jr $ra

exit:li $v0, 10
     syscall
  
        