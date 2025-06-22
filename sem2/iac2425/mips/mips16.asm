.data
lista:      .space 24   
prompt1:    .asciiz "Insira 6 numeros: "
sep:        .asciiz " - "

.text
.globl main

main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      li $t0, 0
      la $t1, lista
      
while: beq $t0, 6, smain
	
        li $v0, 5 #read int
        syscall
        move $a1, $v0
        sw $v0, 0($t1) 
        
	addi $t1, $t1, 4
	addi $t0, $t0, 1
	j while
	
	
smain:	li $s0, 0
	li $t0, 0
	
	
swhile: beq $t0, 6, done
	
        sw $v0, 0($t1) 
        sw $v1, 4($t1) 
        
        bgt $v0, $v1, switch
        
	addi $t1, $t1, 4
	addi $t0, $t0, 1
	beq $s0, 0, done
	j swhile
	
	
	
done: li $v0, 10
      syscall    