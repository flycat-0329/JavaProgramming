package gameCode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class SouthRoomPanel extends MapPrint {
	public SouthRoomPanel(int[][] map, int scope) {
		super(map, scope);
	}
	
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
	
		if(this.x == 3 && this.y == 3) {
			quizStart();
		}
		
	}

	public void quiz1(String issue, String answer) {
		while (true) {
			// 숫자 입력 받기
			String user = JOptionPane.showInputDialog(issue);
			if (user.equals(answer)) {
				JOptionPane.showMessageDialog(null, "맞았습니다");
				break;
			} else {
				JOptionPane.showMessageDialog(null, "틀렸습니다.다시 한번 입력");
			}
		}
	}

	public void quiz2(String issue, String answer) {
		while (true) {
			// 숫자 입력 받기
			String user = JOptionPane.showInputDialog(issue);
			if (user.equals(answer)) {
				JOptionPane.showMessageDialog(null, "맞았습니다. 세번째 비밀번호 = " + password.charAt(2));
				MainRoom mr = new MainRoom();
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(null, "틀렸습니다.다시 한번 입력");
			}
		}
	}

	public void quizStart() {
		quiz1("조건식이 참이면 시행하는 명령문 증가식", "while");
		quiz1("문자열 자료형:", "String");
		quiz2("클래스 객체 문자열에서 해당 인덱스 번호의 문자값을 리턴하는 함수:", "charAt");
	}
}

public class SouthRoom {
	public SouthRoom() {
		// 미로 맵을 2차원 배열로 초기화
		int[][] map = { 
				{ 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 7, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1 } };
		// 틀 준비
		JFrame frame = new JFrame("남쪽방");
		// 패널 준비
		SouthRoomPanel panel = new SouthRoomPanel(map, 5);
		// 틀에 패널 끼우고 실행 준비 완료
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
