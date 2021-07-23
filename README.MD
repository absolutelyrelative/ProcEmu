## ProcEmu
A processor emulator application, allowing electronics / engineering students to emulate Single Cycle, 
Multi Cycle, Pipeline processors and their respective components (ALU, Controller, ALU Controller, 
various memories, Program Counter) to be inspected at each emulated clock and/or instruction.
It also contains its own assembler to assemble instructions into binary code that is loaded in the
Instruction Memory (if Single Cycle / Pipeline).

# Instruction set
*Instructions of type 1:

    bits    data
    0-15    OFFSET
    16-20   RS/RD
    21-25   RO
    26-31   OP

    *LW R1, 200(R2):
        OP:      35
        RO:      02
        RD:      01
        OFFSET: 200
    *SW R1, 200(R2):
        OP:      43
        RO:      02
        RS:      01
        OFFSET: 200

*Instruction of type 2:

    bits    data
    0-5     FUNCT
    6-10    SHIFT (Not implemented)
    11-15   RD
    16-20   RT
    21-25   RS
    26-31   OP (Always 0 for type 2 instructions)

    *ADD R8,R17,R18: (R18 + R17) |---> R8
        OP:       0
        RS:      17
        RT:      18
        RD:      08
        SHAMT:    0
        FUNCT:   32
    *SUB R8,R17,R18: (R18 - R17) |---> R8
        OP:       0
        RS:      17
        RT:      18
        RD:      08
        SHAMT:    0
        FUNCT:   34

*Instructions of type 3:

     bits    data
     0-15    OFFSET
     16-20   RT
     21-25   RS
     26-31   OP (Only beq is implemented, so always 4)

     *BEQ R18,R19,400:
         OP:          4
         RS:         18
         RT:         19
         OFFSET:    400

*Instructions of type 4:
     bits    data
     0-25    OFFSET
     26-31   OP (Only JMP is implemented, so always 2)

     *JMP 400:
         OP:          2
         OFFSET:    400
