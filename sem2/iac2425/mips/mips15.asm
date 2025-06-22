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
      
while: beq $t0, 6, pmain
	
        li $v0, 5 #read int
        syscall
        move $a1, $v0
        sw $v0, 0($t1) 
        
	addi $t1, $t1, 4
	addi $t0, $t0, 1
	j while
	
      
      
pmain:li $t0, 0
      la $t1, lista
      
pwhile: beq $t0, 6, done
	lw $a0, 0($t1)
        li $v0, 1 #print int
        syscall
        la $a0, sep
        li $v0, 4 #print string
        syscall
        
	addi $t1, $t1, 4
	addi $t0, $t0, 1
	j pwhile
	
done: li $v0, 10
      syscall      
