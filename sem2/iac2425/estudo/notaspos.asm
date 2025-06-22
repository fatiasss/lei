.data
msg: .asciiz "O numero de positivas é: "
notas: .word 17, 6, 12, 10, 4

.globl main
.text

main: li $t0, 0 #num = 0
      li $t1, 0 #i = 0
      la $s0, notas #s0 = notas

for: bge $t1, 5, done
     sll $t2, $t1, 2 #t2 = i*4 = offset
     add $t3, $t2, $s0 # $t3 = notas + offset = notas[i]
     lw $s1, 0($t3)
     blt $s1, 10, not_if
     bgt $s1, 20, not_if
     addi $t0, $t0, 1
     addi $t1, $t1, 1
     j for
     
not_if: addi $t1, $t1, 1
        j for


done: la $a0, msg
      li $v0, 4
      syscall
      la $a0, ($t0)
      li $v0, 1
      syscall
      li $v0, 10
      syscall