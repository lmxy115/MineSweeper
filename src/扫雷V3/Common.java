package ɨ��V3;

public interface Common {
	
	public static final int ROW=10;//������Ŀ
	public static final int LIST=10;//������Ŀ
	public static final int X=6;//��ʼ����
	public static final int Y=10;
	public static final int SIZE=30;//��С
	public static final int MineArea[][]=new int [ROW-1][LIST-1];//��ά��������洢
	public static final RecordList RL=new RecordList();//����������н��м�¼����ʾͼƬ��λ��
	public static final int Flag[][]=new int[ROW-1][LIST-1];
	public static final RecordList FlagList=new RecordList();//����������н��м�¼����ʾ��ӛͼƬ��λ��
	public static final int Score=0;
	public static final Flag f=new Flag();

		

}
