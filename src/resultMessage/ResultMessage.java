package resultMessage;

import javax.swing.JOptionPane;

public class ResultMessage {
	public static void wrong(String title,String msg)//�Ի�����ʾ��Ϣ
	{
		JOptionPane.showMessageDialog(null, msg,title,JOptionPane.YES_NO_CANCEL_OPTION);
	}
	public static void right(String title,String msg)//�Ի�����ʾ��Ϣ
	{
		JOptionPane.showMessageDialog(null, msg,title,JOptionPane.YES_NO_CANCEL_OPTION);
	}
}
