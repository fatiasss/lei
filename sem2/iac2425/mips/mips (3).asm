
li $t1, 0x12345678

andi $t2, $t1, 0xF0000000
srl $t2, $t2, 28
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall

andi $t2, $t1, 0x0F000000
srl $t2, $t2, 24
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall

andi $t2, $t1, 0x00F00000
srl $t2, $t2, 20
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall

andi $t2, $t1, 0x000F0000
srl $t2, $t2, 16
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall


andi $t2, $t1, 0x0000F000
srl $t2, $t2, 12
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall

andi $t2, $t1, 0x00000F00
srl $t2, $t2, 8
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall

andi $t2, $t1, 0x000000F0
srl $t2, $t2, 4
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall

andi $t2, $t1, 0x0000000F
move $a0, $t2

li $v0, 34
syscall
li $v0, 11
li $a0, 0x20
syscall




