.data
lista:      .word 4, 3, -2, 1, 27, 45       # Declare and initialize array
prompt1:    .asciiz "O conteudo do Array é:\n"
sep:        .asciiz " - "

.text
.globl main

main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      li $t0, 0
      la $t1, lista
      
while: beq $t0, 6, done
	lw $a0, 0($t1)
        li $v0, 1 #print int
        syscall
        la $a0, sep
        li $v0, 4 #print string
        syscall
        
	addi $t1, $t1, 4
	addi $t0, $t0, 1
	j while
	
done: li $v0, 10
      syscall

	
