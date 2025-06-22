.data
minus: .asciiz "TEXTO EM MAIUSCULAS"
maius: .space 20

.text
.globl main
main: li $t0, 0

while: lb $t1, minus($t0)
    beqz $t1, done 
    li $t2, 'A'
    li $t3, 'Z'
    
    blt $t1, $t2, skip_convert
    bgt $t1, $t3, skip_convert

    li $t5, 32
    add $t1, $t1, $t5
    
skip_convert: sb $t1, maius($t0) 
	      addi $t0, $t0, 1
              j while
              

done: li $v0, 4   
      la $a0, maius        
      syscall
    

    

