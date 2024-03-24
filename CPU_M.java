

//�ֻ��� Ŭ����
//�� �Ѱ��� ���μ��� �������͸� ���� ��ǻ��
public abstract class CPU_M {
	protected static short[] MEMORY; 	//�޸�
	protected static short DR;				//������ - �޸𸮿��� �о�� �ǿ����ڸ� ���� (16)
	protected static short AR;				//�޸� �ּ� - �޸��� �ּҸ� ��Ÿ�� (4096�����̹Ƿ� 12��Ʈ�� ����)
	protected static short AC;				//����� - ���� ó�� �������� (16)
	protected static short IR;					//��ɾ� - �޸𸮿��� �о�� ��ɾ� ���� (16)
	protected static short PC;				//���α׷� ī���� - �޸��� �ּҸ� ��Ÿ�� (12)
	protected static short TR;				//�ӽ� - ��� ������ �ӽ� ������ (16)
	protected static byte INPR;			//�Է� - ����� ��ġ�κ��� 8��Ʈ ���� ���� �ۼ��� (8)
	protected static byte OUTR;			//��� - �����. (8)
	
	
	public void CPU() {
		
		//�޸� ����
		MEMORY = new short[4096];
			
		//�������� ����
		DR = 0;
		AR = 0;
		TR = 0;
	}
	
	//�޸𸮿� �����͸� ����ִ� �Լ�
	//�Ű����� (�迭�� ��ġ, ��)
	public abstract void inputData(int position, short data);
	
	//��ɾ� �и� (opcode �� address)
	public abstract void decodeInstruction(short instruction);

	//opcode �ص�
	public abstract void decodeOpcode(); 
	
	//��ɾ� �Ǻ�
	public abstract void readInstruction();
}
