.data
prompt1: .asciiz "Introduza um numero\n"
strpar: .asciiz  "O numero é par\n"
strimp: .asciiz  "O numero é impar\n"

.text
.globl main

main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      
      li $v0, 5 #read int
      syscall
      move $t0, $v0 #store in t0
      
      
      andi $t1, $t0, 1
      
      beq $t1, 0, target
      la $a0, strimp
      li $v0, 4
      syscall
      j end
      
target:la $a0, strpar
       li $v0, 4
       syscall
       
end: li $v0, 10
     syscall
      
      
      
