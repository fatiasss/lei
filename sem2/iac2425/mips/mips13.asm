.data
prompt1: .asciiz "Introduza uma string\n"
result: .asciiz "O número de carateres numéricos: "
str: .space 40

.text
.globl main
main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      la $a0, str #save in str
      li $a1, 40 #size
      li $v0, 8 #read string
      syscall
      li $t0, 0
      li $t3, 0

while: lb $t1, str($t0)
	beqz $t1, done
	addi $t0, $t0, 1
	ble $t1, 48, skip
	bge $t1, 57, skip
	addi $t3, $t3, 1
	j while
	
skip: addi $t0, $t0, 1
      j while

done: la $a0, result
      li $v0, 4 #print string
      syscall
      
      move $a0, $t3
      li $v0, 1 #print int
      syscall
      
      li $v0, 10
      syscall

