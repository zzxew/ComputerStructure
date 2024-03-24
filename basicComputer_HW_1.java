

public class basicComputer extends CPU-M {	
	
	public static void main(String[] args){
		
		// basicComputer 클래스를 생성한다.
		basicComputer  bsc = new basicComputer();
		
		//메모리에 임의 명령어를 입력한다.
		MEMORY[0] = (short)0xf800;
		
		//메모리 0번에서 명령어 읽음.
		short testInstruction = MEMORY[0];
		
		// 그냥 출력하면 10진수로 계산되어 출력되므로 출력 포맷을 변경해준다.
		String ss = String.format("%04xH", testInstruction);
		System.out.println(" 01. 입력 = " + ss);
		
		//명령어 해독 실행
		bsc.decodeInstruction(testInstruction);
	}
	
	public basicComputer(){
		super.CPU();
	}
	
	@Override
	public void inputData(int position, short data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decodeInstruction(short instruction) {
		// TODO Auto-generated method stub
		
		// Instruction Register에 명령어 저장
		IR = instruction;
		
		// 명령어 타입을 구분 (12비트 우측 쉬프트하여 최상단 4비트만 남김)
		byte type = [                           ]; 
		String typeString = String.format("%02x", type);
		
		// 구분해낸 명령어 문자열을 통해 명령어 형식별로 결과를 출력함
		switch (typeString) {
		case "07":
			System.out.println(" 02. 명령어 형식 = 'Register' reference operation");
			System.out.println(" 03. Symbol = " + HexToString(IR));
			break;
		case "ff":
			System.out.println(" 02. 명령어 형식 = 'I/O' operation");
			System.out.println(" 03. Symbol = " + HexToString(IR));
			break;
		default:
			System.out.println(" 02. 명령어 형식 = 'Memory' reference operation");
			System.out.println(" 03. Symbol = " + mHexToString(type));
			// 주소모드를 나타내는 4비트를 밀어버리고 12비트의 Address만 남김			
			TR = (short) ((IR<<4));
			System.out.println(" 04. Address = " + Integer.toHexString(TR/16) + "H");
			
			break;
		}
			
	}
	
	// decodeInstruction 함수에서 레지스터 참조, 입출력연산의 경우 헥사코드 변환을 위해 호출
	public String HexToString(short hex){
		switch (hex) {
		// 레지스터 참조 명령어
		case 0x7800:			return "CLA";
		case 0x7400:			return "CLE";
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]

			
		// I/O 명령어
		case (short) 0xf800:			return "INP";
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		
			
		// 메모리 참조 명령어 (다른 함수 사용)
			default:				return "Unknown";
		}
		
	}
	
	
	// 메모리 참조명령
	public String mHexToString(byte hex) {
		switch (hex) {
		// I = 0 간접
		case 0x00:		return "[ Indirect ] AND";
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		
			
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

	@Override
	public void decodeOpcode() {
		// TODO Auto-generated method stub		
		
	}

	@Override
	public void readInstruction() {
		// TODO Auto-generated method stub
		
	}

}
