.data  
prompt1:    .asciiz "Introduza um numero\n"
numstring:    .asciiz "O factorial do número " 
e:    .asciiz " é "



.text
.globl main


main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      li $v0, 5 #read int
      syscall
      move $a1, $v0
      jal factorial
      la $a0, numstring
      li $v0, 4 #print string
      syscall
      move $a0, $a1
      li $v0, 1 #print int
      syscall
      la $a0, e
      li $v0, 4 #print string
      syscall
      move $a0, $v1
      li $v0, 1 #print int
      syscall
exit: li $v0, 10 #exit
      syscall

factorial:
	  move $t0, $a1 #num saved
	  li $v1, 1
	  
	  
fact_loop:
	  beq $t0, $0, stop
	  mul $v1, $v1, $t0
	  subi $t0, $t0, 1
	  j fact_loop
	  
stop: jr $ra


      
      
      
      
      
	 
	  
	  
	  