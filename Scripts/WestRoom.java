package gameCode;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class WestRoomPanel extends MapPrint{
	public WestRoomPanel(int[][] map) {
		super(map, 3);
	}
	
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);

		if(this.x == 29 && this.y == 13) {
			JOptionPane.showMessageDialog(this, "게임 클리어! 출구에 도착했습니다. 두번째 비밀번호 = " + password.charAt(1));
			MainRoom mr = new MainRoom();
			JFrame parent = (JFrame) this.getTopLevelAncestor();
			parent.dispose();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int y = this.y - SCOPE, y2 = 0; y <= this.y + SCOPE; y++, y2++) {
			for (int x = this.x - SCOPE, x2 = 0; x <= this.x + SCOPE; x++, x2++) {
				int index = WALL;
				if ((0 <= x) && (x <= maxX) && (0 <= y) && (y <= maxY))
					index = map[y][x];
				g.drawImage(this.image[index], x2 * SIZE, y2 * SIZE, SIZE, SIZE, null);
			}
		}
		g.drawImage(this.image[2], SCOPE * SIZE, SCOPE * SIZE, SIZE, SIZE, null);
	}
}

public class WestRoom {
	public WestRoom() {
		//미로맵
		int[][] maze = {
	            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
	            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
	            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
	            {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
	            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
	            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
	            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
	            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
	            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
	            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
	            {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
	            {1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 6},
	            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	        };
		// 틀 준비
			JFrame frame = new JFrame( "서쪽방" );
			// 패널 준비
			WestRoomPanel panel = new WestRoomPanel( maze );
			// 틀에 패널 끼우고 실행 준비 완료
			frame.getContentPane().add( panel );
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
	}
	
}
