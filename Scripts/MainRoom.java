package gameCode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class MainRoomPanel extends MapPrint{
	public MainRoomPanel(int[][] map, int SCOPE) {
		super(map, SCOPE);
	}
	
	//방 입장 시 질문용 팝업
	int doorOptionPane(String roomName) {
		int res = JOptionPane.showConfirmDialog(null, 
				roomName, "Confirm", 
				JOptionPane.YES_NO_OPTION);
		return res;
	}
	
	@Override
	//방 입장 시 또는 비밀번호 입력 시 이벤트 처리
	public void move(int dx, int dy) {
		super.move(dx, dy);
		
		if(this.x == 5 && this.y == 5) {
			String user = JOptionPane.showInputDialog("비밀번호를 입력해주세요");
			if (user.equals(password)) {
				DialogueFrame d = new DialogueFrame(1);
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(null, "틀렸습니다.");
			}
		}
		
		if(this.x == 5 && this.y == 0) {
			int a = doorOptionPane("북쪽방에 들어가시겠습니까?");
			if(a == JOptionPane.YES_OPTION) {
				NorthRoom nr = new NorthRoom();
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
			}
			else {
				super.move(0, 1);
			}
		}
		
		if(this.x == 5 && this.y == 10) {
			int a = doorOptionPane("남쪽방에 들어가시겠습니까?");
			if(a == JOptionPane.YES_OPTION) {
				SouthRoom sr = new SouthRoom();
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
			}
			else {
				super.move(0, -1);
			}
		}
		
		if(this.x == 0 && this.y == 5) {
			int a = doorOptionPane("서쪽방에 들어가시겠습니까?");
			if(a == JOptionPane.YES_OPTION) {
				WestRoom wr = new WestRoom();
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
			}
			else {
				super.move(1, 0);
			}
		}
		
		if(this.x == 10 && this.y == 5) {
			int a = doorOptionPane("동쪽방에 들어가시겠습니까?");
			if(a == JOptionPane.YES_OPTION) {
				EastRoom er = new EastRoom();
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
			}
			else {
				super.move(-1, 0);
			}
		}
	}
}

public class MainRoom {
	public MainRoom(){
		// 미로 맵을 2차원 배열로 초기화
		int[][] map = { 
				{ 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 6, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1 } };
		// 틀 준비
		JFrame frame = new JFrame("중앙방");
		// 패널 준비
		MainRoomPanel panel = new MainRoomPanel(map, 5);
		// 틀에 패널 끼우고 실행 준비 완료
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		DialogueFrame d = new DialogueFrame(0);
	}
}
