package gameCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;

// 캐릭터 패널
class EastRoomPanel extends MapPrint {
//	Timer timer;
//	TrapThread trapThread;
	int[][] warnings = {};
	int[][] traps = {};
	boolean isClear = false;

	private int[][] trapReset() {
		Random r = new Random();
		int[][] a = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
				{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
				{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
				{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
				{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
				{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
				{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
				{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {} };

		for (int i = 0; i < 200; i++) {
			int qwe = (int) (Math.random() * 80) + 1;
			int asd = (int) (Math.random() * 5) + 1;
			a[i] = new int[] { qwe, asd };
		}

		return a;
	}

	public EastRoomPanel(int[][] map) {
		super(map, 5);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					if(isClear) {
						timer.cancel();
					}
					
					warnings = trapReset();
					repaint();
					Thread.sleep(800);

					traps = warnings;
					int[][] a = {};
					warnings = a;
					repaint();
					Thread.sleep(200);

					traps = a;
					repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 2000);
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);

		for (int[] trap : traps) {
			if (this.x == trap[0] && this.y == trap[1] && isClear == false) {
				JOptionPane.showMessageDialog(this, "함정에 빠졌습니다! 게임이 다시 시작됩니다.");
				restartGame();
				return;
			}
		}

		if (this.x == 81 && this.y == 3) {
			isClear = true;
			JOptionPane.showMessageDialog(this, "게임 클리어! 출구에 도착했습니다.  첫번째 비밀번호 = " + password.charAt(0));
			MainRoom mr = new MainRoom();
			JFrame parent = (JFrame) this.getTopLevelAncestor();
			parent.dispose();
			return;
		}
	}

	// 게임 재시작 메서드
	private void restartGame() {
		this.x = 1;
		this.y = 1;
		repaint(); // 화면 업데이트
	}

	@Override
	// 캐릭터를 중심으로 일정 범위내 주변 맵으로 화면 업데이트
	public void paint(Graphics g) {
		super.paint(g);
		for (int y = this.y - SCOPE, y2 = 0; y <= this.y + SCOPE; y++, y2++) {
			for (int x = this.x - SCOPE, x2 = 0; x <= this.x + SCOPE; x++, x2++) {
				int index = WALL;
				if ((0 <= x) && (x <= maxX) && (0 <= y) && (y <= maxY))
					index = map[y][x];
				for (int[] k : warnings) {
					if (k[0] == x && k[1] == y) {
						index = WARNING;
					}
				}
				for (int[] k : traps) {
					if (k[0] == x && k[1] == y) {
						index = TRAP;
					}
					if (this.x == k[0] && this.y == k[1]) {
						JOptionPane.showMessageDialog(this, "함정에 빠졌습니다! 게임이 다시 시작됩니다.");
						restartGame();
						return;
					}
				}
				g.drawImage(this.image[index], x2 * SIZE, y2 * SIZE, SIZE, SIZE, null);
			}
		}
		g.drawImage(this.image[2], SCOPE * SIZE, SCOPE * SIZE, SIZE, SIZE, null);

		// 현재 좌표 텍스트를 빨간색으로 표시
		g.setColor(Color.RED); // 텍스트 색상을 빨간색으로 설정
		g.setFont(new Font("Arial", Font.BOLD, 14)); // 폰트 설정
		g.drawString("(" + this.x + ", " + this.y + ")", 10, SIZE * (2 * SCOPE + 1) - 10);
	}
}

public class EastRoom {
	public EastRoom() {

		// 미로 맵을 2차원 배열로 초기화
		int[][] map = {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, };
		// 틀 준비
		JFrame frame = new JFrame("동쪽방");
		// 패널 준비
		EastRoomPanel panel = new EastRoomPanel(map);
		// 틀에 패널 끼우고 실행 준비 완료
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
