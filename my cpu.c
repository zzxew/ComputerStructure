#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

typedef struct

{
	unsigned short MEMORY[4096];
	short DR;
	unsigned short AR;
	short AC;
	unsigned short IR;
	unsigned short PC;
	short TR;
	short INPR;
	short OUTR;


} CPU_M;



void decodeinstruction(short instruction, CPU_M cpu);

char* hextostring(short IR);

char* mhextostring(unsigned char type);

void readinstruction(unsigned char opcode, unsigned char I, CPU_M cpu);

void decodeopcode(unsigned short IR, CPU_M cpu);




int main(void)

{
	CPU_M cpu;

	for (int i = 0; i < 4096; i++)
	{
		cpu.MEMORY[i] = 0;
	}



	cpu.MEMORY[2828] = 0x7800;
	unsigned short testinstruction = cpu.MEMORY[2828];

	cpu.MEMORY[0] = 0x9234;
	cpu.MEMORY[1] = 0x9020;
	cpu.MEMORY[32] = 0x7002;  // MEMORY[20]
	cpu.MEMORY[564] = 0xf400; // MEMORY[234]


	printf(" 01. 입력 = %4XH\n", testinstruction);
	decodeinstruction(testinstruction, cpu);

	printf("------ 테스트 종료 ------\n");
	printf("\n");

	printf(" 00. basiccoumputer 프로그램 시작\n");

	cpu.PC = 0;
	while (true)
	{
		printf(" | AR <- PC |\n");
		cpu.AR = cpu.PC;

		if (cpu.MEMORY[cpu.AR] != 0)
		{
			printf(" | IR <-M[AR], PC <- PC + 1 | \n");
			printf("\n");
			cpu.IR = cpu.MEMORY[cpu.AR];
			cpu.PC = cpu.PC + 1;
			decodeopcode(cpu.IR, cpu);
		}
		else
		{
			printf(" 04. 더 이상 수행할 명령어가 없으므로 프로그램 종료");
			break;
		}

	}


}


void decodeinstruction(short instruction, CPU_M cpu)

{
	cpu.IR = instruction;
	unsigned char type = (cpu.IR >> 12);

	switch (type)
	{
	case 7:
		printf(" 02. 명령어 형식 = Register reference operation\n");
		printf(" 03. Symbol = %s\n", hextostring(cpu.IR));
		break;
	case 0xf:
		printf(" 02. 명령어 형식 = 'I/O' operation\n");
		printf(" 03. Symbol = %s\n", hextostring(cpu.IR));
		break;
	default:
		printf(" 02. 명령어 형식 = Memory reference operarion\n");
		printf(" 03. Symbol = %s\n", mhextostring(type));
	}


}



char* hextostring(short IR)

{
	unsigned hex = IR;

	switch (hex) {
		// 레지스터 참조 명령어
	case 0x7800:			return "CLA";
	case 0x7400:			return "CLE";
	case 0x7200:			return "CMA";
	case 0x7100:			return "CME";
	case 0x7080:			return "CIR";
	case 0x7040:			return "CIL";
	case 0x7020:			return "INC";
	case 0x7010:			return "SPA";
	case 0x7008:			return "SNA";
	case 0x7004:			return "SZA";
	case 0x7402:			return "SZE";
	case 0x7401:			return "HLT";




		// I/O 명령어
	case (short)0xf800:			return "INP";
	case (short)0xf400:			return "OUT";
	case (short)0xf200:			return "SKI";
	case (short)0xf100:			return "SKO";
	case (short)0xf080:			return "ION";
	case (short)0xf840:			return "IOF";


		// 메모리 참조 명령어 (다른 함수 사용)
	default:				return "Unknown";
	}
}


char* mhextostring(unsigned char type)

{

	switch (type) {
		// I = 0 간접
	case 0x00:		return "[ Indirect ] AND";
	case 0x01:		return "[ InDirect ] ADD";
	case 0x02:		return "[ InDirect ] LDA";
	case 0x03:		return "[ InDirect ] STA";
	case 0x04:		return "[ InDirect ] BUN";
	case 0x05:		return "[ InDirect ] BSA";
	case 0x06:		return "[ InDirect ] ISZ";

		// I = 1 직접
	case 0x08:		return "[ Direct ] AND";
	case 0x09:		return "[ Direct ] ADD";
	case 0x0a:		return "[ Direct ] LDA";
	case 0x0b:		return "[ Direct ] STA";
	case 0x0c:		return "[ Direct ] BUN";
	case 0x0d:		return "[ Direct ] BSA";
	case 0x0e:		return "[ Direct ] ISZ";

		// unknown
	default:
		return "Unknown";
	}
}



void decodeopcode(unsigned short IR, CPU_M cpu)

{
	printf("\n");
	printf(" 01. 입력 = %4XH\n", IR);
	printf(" | Decode operation code in IR(12-14) |\n");
	unsigned char opcode = (char)(IR >> 12);
	cpu.AR = ((unsigned short)(IR << 4) >> 4);
	unsigned char I = (char)(IR >> 15);
	printf(" | AR <- IR(0-11), I <- IR(15) |\n");
	readinstruction(opcode, I, cpu);
}


void readinstruction(unsigned char opcode, unsigned char I, CPU_M cpu)

{
	switch (opcode)
	{
	case 7:
		printf(" | D7 = 1 | \n");
		printf(" 02. 동작 = Register reference 명령어 수행\n");
		printf(" | I = %1d |\n", I);
		if (cpu.IR == 0x7002)
		{
			printf(" 03. symbol = SZE\n");
			printf("");
		}
		break;
	case 0xf:
		printf(" | D7 = 1 | \n");
		printf(" 02. 동작 = I/O reference 명령어 수행\n");
		printf(" | I = %1d\n", I);
		if (cpu.IR == 0xf400)
		{
			printf(" 03. Symbol = OUT\n");
		}
		break;
	default:
		printf(" | D7 = 0 |\n");
		printf(" 02. 동작 = Memory reference 명령어 수행\n");
		if (I == 1)
		{
			printf(" 03. Symbol = unknown \n");
			printf(" | AR <- M[AR] |\n");
			cpu.IR = cpu.MEMORY[cpu.AR];
			cpu.AR = (unsigned)(cpu.MEMORY[cpu.AR] << 4);
			cpu.AR = (unsigned)(cpu.AR >> 4);

			decodeopcode(cpu.IR, cpu);
		}
		else
		{
			break;
		}

	}
}