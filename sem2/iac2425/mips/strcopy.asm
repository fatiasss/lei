.data
frase1:     .space 20
frase2:     .space 20
frase3:     .space 40

msg1:       .asciiz "\nInsira a frase1: "
msg2:       .asciiz "\nInsira a frase2: "
msg3:       .asciiz "\n O numero de caracteres da frase1 é: "
msg4:       .asciiz "\nA frase concatenada é: "

.text
.globl main

main:
     # print_string("\nInsira a frase1: ")
    li $v0, 4
    la $a0, msg1
    syscall

    # read_string(frase1, 20)
    li $v0, 8
    la $a0, frase1
    li $a1, 20
    syscall

    # print_string("\nInsira a frase2: ")
    li $v0, 4
    la $a0, msg2
    syscall

    # read_string(frase2, 20)
    li $v0, 8
    la $a0, frase2
    li $a1, 20
    syscall

    # print_string("\n O numero de caracteres da frase1 é: ")
    li $v0, 4
    la $a0, msg3
    syscall

    # n = strlen(frase1)
    la $a0, frase1
    jal strlen

    # print_int10(n)
    move $a0, $v0     # strlen result in $v0
    li $v0, 1
    syscall

    # str_copy(frase3, frase1)
    la $a0, frase1
    la $a1, frase3
    jal strcopy

    # str_cat(frase3, frase2)
    la $a0, frase2
    la $a1, frase3
    jal strcat

    # print_string("\nA frase concatenada é: ")
    li $v0, 4
    la $a0, msg4
    syscall

    # print_string(frase3)
    li $v0, 4
    la $a0, frase3
    syscall

    # exit()
    j exit
     
      


	
	
strcopy:  lb $t0, 0($a0) #src
        beq $t0, '\0', strcopy_done
        sb $t0, 0($a1) 
        addi $a0, $a0, 1 
        addi $a1, $a1, 1
        j strcopy
        
strcopy_done: li $t0, 0
      sb $t0, 0($a1)
      jr $ra
      
      
strcat: lb $t0, 0($a1) #src
	beq $t0, '\0', strcatcopy
	addi $a1, $a1, 1
	j strcat
	
	
strcatcopy:  jal strcopy
    	     jr $ra
    	     
strlen:
	li $t0, 0 #i
	li $v1, 0 #n counter
	
	
while:  lb $t2, 0($a0)
        beq $t2, '\0', strlen_done
        addi $v1, $v1, 1
        addi $a0, $a0, 1 
        addi $t0, $t0, 1
        j while
        
strlen_done: move $a1, $t1
      jr $ra
	
      
                  
      
      
      
exit:li $v0, 10
     syscall