package gameCode;

//북쪽방
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 캐릭터 패널
class NorthRoomPanel extends MapPrint {
	private boolean visitedKey = false; // 키 위치 방문 여부
	private int[][] traps = {{}, {}, {}, {}, {}}; // 함정 좌표 배열
	// 캐릭터 패널 준비

	public NorthRoomPanel(int[][] map){
		super(map);
		
		//보이지 않는 함정 배치
		for (int i = 0; i < 5; i++) {
			int qwe = (int) (Math.random() * 9) + 1;
			int asd = (int) (Math.random() * 9) + 1;
			traps[i] = new int[] { qwe, asd };
		}
	}

	// 새 위치를 이동 가능한 위치로 조정
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
		
		for (int[] trap : traps) {
			if (this.x == trap[0] && this.y == trap[1]) {
				JOptionPane.showMessageDialog(this, "함정에 빠졌습니다! 게임이 다시 시작됩니다.");
				restartGame();
				return;
			}
		}
		// 키 위치에 도달하면 visitedKey를 true로 설정
		if (this.x == keyX && this.y == keyY) {
			visitedKey = true;
		}

		// 출구에 도달했는지 확인
		if (this.x == 9 && this.y == 9) {
			if (visitedKey) {
				JOptionPane.showMessageDialog(this, "게임 클리어! 출구에 도착했습니다. 네번째 비밀번호 = " + password.charAt(3));
				MainRoom mr = new MainRoom();
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(this, "출구에 도착하려면 키 위치를 먼저 지나야 합니다.");
				// 출구에 도달했지만 키를 지나지 않았다면 출구로 이동하지 않음
				this.x -= dx;
				this.y -= dy;
			}
		}
	}

	// 게임 재시작 메서드
	private void restartGame() {
		this.x = 1;
		this.y = 1;
		this.visitedKey = false; // 키 방문 상태 초기화
		repaint(); // 화면 업데이트
	}

}

public class NorthRoom {
	public NorthRoom() {
		// 미로 맵을 2차원 배열로 초기화
		int[][] map = { 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		// 틀 준비
		JFrame frame = new JFrame("북쪽방");
		// 패널 준비
		NorthRoomPanel panel = new NorthRoomPanel(map);
		// 틀에 패널 끼우고 실행 준비 완료
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
