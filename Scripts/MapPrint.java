package gameCode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MapPrint extends JPanel{

	public int x, y, maxX, maxY, keyX, keyY;
	public int[][] map;
	public Image[] image;
	public int SCOPE = 3;
	public final int SIZE = 40, CHARACTER = 2, WALL = 1, KEY = 3, WARNING = 4, TRAP = 5, DOOR = 6;
	final String imagePath = "C:\\Users\\song_\\eclipse-workspace\\TheEscape\\image\\";
	public static String password = "";
	
	public MapPrint(int[][] map) {
		if(password.equals("")) {
			password = String.format("%04d", (int) Math.random() * 9999);
		}
		
		// 캐릭터 초기화 : 미로 맵에서 위치 및 이동 가능 범위
		this.x = 1;
		this.y = 1;
		this.map = map;
		this.maxX = map[0].length - 1;
		this.maxY = map.length - 1;
		this.keyX = 5;
		this.keyY = 5;

		// 이미지 초기화 : 캐릭터, 길, 벽
		this.image = new Image[8];
		this.image[0] = new ImageIcon(imagePath + "path.png").getImage();
		this.image[1] = new ImageIcon(imagePath + "wall.png").getImage();
		this.image[2] = new ImageIcon(imagePath + "character.png").getImage();
		this.image[3] = new ImageIcon(imagePath + "key.png").getImage();
		this.image[4] = new ImageIcon(imagePath + "warning.png").getImage();
		this.image[5] = new ImageIcon(imagePath + "trap.png").getImage();
		this.image[6] = new ImageIcon(imagePath + "door.png").getImage();
		this.image[7] = new ImageIcon(imagePath + "quiz.png").getImage();
		setPreferredSize(new Dimension(SIZE * (2 * SCOPE + 1), SIZE * (2 * SCOPE + 1)));
		// 키보드 입력 시 반응할 리스너 등록
		addKeyListener(new ArrowKeyListener());
		setFocusable(true);
		requestFocus();
	}
	
	public MapPrint(int[][] map, int SCOPE) {
		if(password.equals("")) {
			password = String.format("%04d", (int) Math.random() * 9999);
		}
		
		// 캐릭터 초기화 : 미로 맵에서 위치 및 이동 가능 범위
		this.x = 1;
		this.y = 1;
		this.map = map;
		this.maxX = map[0].length - 1;
		this.maxY = map.length - 1;
		this.keyX = 5;
		this.keyY = 5;
		this.SCOPE = SCOPE;

		// 이미지 초기화 : 캐릭터, 길, 벽
		this.image = new Image[8];
		this.image[0] = new ImageIcon(imagePath + "path.png").getImage();
		this.image[1] = new ImageIcon(imagePath + "wall.png").getImage();
		this.image[2] = new ImageIcon(imagePath + "character.png").getImage();
		this.image[3] = new ImageIcon(imagePath + "key.png").getImage();
		this.image[4] = new ImageIcon(imagePath + "warning.png").getImage();
		this.image[5] = new ImageIcon(imagePath + "trap.png").getImage();
		this.image[6] = new ImageIcon(imagePath + "door.png").getImage();
		this.image[7] = new ImageIcon(imagePath + "quiz.png").getImage();
		setPreferredSize(new Dimension(SIZE * (2 * SCOPE + 1), SIZE * (2 * SCOPE + 1)));
		// 키보드 입력 시 반응할 리스너 등록
		addKeyListener(new ArrowKeyListener());
		setFocusable(true);
		requestFocus();
	}
	
	public class ArrowKeyListener extends KeyAdapter {
		// 키보드 입력 시 반응
		public void keyPressed(KeyEvent e) {
			// 입력키에 따라 상하좌우로 이동후 화면 업데이트
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_LEFT:
			case 'A':
			case 'a':
				move(-1, 0);
				break;
			case KeyEvent.VK_RIGHT:
			case 'D':
			case 'd':
				move(+1, 0);
				break;
			case KeyEvent.VK_UP:
			case 'W':
			case 'w':
				move(0, -1);
				break;
			case KeyEvent.VK_DOWN:
			case 'S':
			case 's':
				move(0, +1);
				break;
			}
			repaint();
		}
	}
	
	public void move( int dx, int dy ) {
		int newX = this.x + dx;
		int newY = this.y + dy;

		// 범위 내에서만 이동
		if (newX < 0 || newY < 0 || newX > maxX || newY > maxY || map[newY][newX] == WALL) {
			return; // 벽이거나 범위를 벗어나면 이동하지 않음
		}

		this.x = newX;
		this.y = newY;
	}
	
	@Override
	// 캐릭터를 중심으로 일정 범위내 주변 맵으로 화면 업데이트
	public void paint(Graphics g) {
		super.paint(g);
		for (int y = this.y - SCOPE, y2 = 0; y <= this.y + SCOPE; y++, y2++) {
			for (int x = this.x - SCOPE, x2 = 0; x <= this.x + SCOPE; x++, x2++) {
				int index = WALL;
				if ((0 <= x) && (x <= maxX) && (0 <= y) && (y <= maxY)) {
					g.drawImage(this.image[0], x2 * SIZE, y2 * SIZE, SIZE, SIZE, null);
					index = map[y][x];
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
