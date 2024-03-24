

public class basicComputer extends CPU-M {	
	
	public static void main(String[] args){
		
		// basicComputer Ŭ������ �����Ѵ�.
		basicComputer  bsc = new basicComputer();
		
		//�޸𸮿� ���� ��ɾ �Է��Ѵ�.
		MEMORY[0] = (short)0xf800;
		
		//�޸� 0������ ��ɾ� ����.
		short testInstruction = MEMORY[0];
		
		// �׳� ����ϸ� 10������ ���Ǿ� ��µǹǷ� ��� ������ �������ش�.
		String ss = String.format("%04xH", testInstruction);
		System.out.println(" 01. �Է� = " + ss);
		
		//��ɾ� �ص� ����
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
		
		// Instruction Register�� ��ɾ� ����
		IR = instruction;
		
		// ��ɾ� Ÿ���� ���� (12��Ʈ ���� ����Ʈ�Ͽ� �ֻ�� 4��Ʈ�� ����)
		byte type = [                           ]; 
		String typeString = String.format("%02x", type);
		
		// �����س� ��ɾ� ���ڿ��� ���� ��ɾ� ���ĺ��� ����� �����
		switch (typeString) {
		case "07":
			System.out.println(" 02. ��ɾ� ���� = 'Register' reference operation");
			System.out.println(" 03. Symbol = " + HexToString(IR));
			break;
		case "ff":
			System.out.println(" 02. ��ɾ� ���� = 'I/O' operation");
			System.out.println(" 03. Symbol = " + HexToString(IR));
			break;
		default:
			System.out.println(" 02. ��ɾ� ���� = 'Memory' reference operation");
			System.out.println(" 03. Symbol = " + mHexToString(type));
			// �ּҸ�带 ��Ÿ���� 4��Ʈ�� �о������ 12��Ʈ�� Address�� ����			
			TR = (short) ((IR<<4));
			System.out.println(" 04. Address = " + Integer.toHexString(TR/16) + "H");
			
			break;
		}
			
	}
	
	// decodeInstruction �Լ����� �������� ����, ����¿����� ��� ����ڵ� ��ȯ�� ���� ȣ��
	public String HexToString(short hex){
		switch (hex) {
		// �������� ���� ��ɾ�
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

			
		// I/O ��ɾ�
		case (short) 0xf800:			return "INP";
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		
			
		// �޸� ���� ��ɾ� (�ٸ� �Լ� ���)
			default:				return "Unknown";
		}
		
	}
	
	
	// �޸� �������
	public String mHexToString(byte hex) {
		switch (hex) {
		// I = 0 ����
		case 0x00:		return "[ Indirect ] AND";
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		[                                            ]
		
			
		// I = 1 ����
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
