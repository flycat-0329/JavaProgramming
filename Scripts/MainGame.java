package test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.Thread.State;

import javax.swing.JFrame;

import java.lang.System;

public class MainGame extends JFrame implements KeyListener {
		EastRoom eastRoom = new EastRoom();
		
		

	    public MainGame() throws InterruptedException, IOException {
//	        System.out.print("\033[H\033[2J");  
//	        System.out.flush();  //콘솔 지우기

	    	for (int i = 0; i < 50; ++i) System.out.println();
	        setTitle("WASD Key Listener Example");		// JFrame 기본 설정
	        setSize(100, 100);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        // KeyListener를 JFrame에 추가
	        addKeyListener(this);
	        setFocusable(true); // 키 입력 포커스를 JFrame으로 설정
	        setVisible(true);
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
	        // 오버라이드 용도
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        int keyCode = e.getKeyCode();

	        switch (keyCode) {
	            case KeyEvent.VK_W:
	                System.out.println("W 키가 눌렸습니다.");
	                break;
	            case KeyEvent.VK_A:
	            	if(eastRoom.getState() == State.NEW) {
		            	eastRoom.start();
	            	}
	                System.out.println("A 키가 눌렸습니다.");
	                break;
	            case KeyEvent.VK_S:
	                System.out.println("S 키가 눌렸습니다.");
	                break;
	            case KeyEvent.VK_D:
	                System.out.println("D 키가 눌렸습니다.");
	                break;
	            default:
	                // 다른 키가 눌렸을 때
	                break;
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	        int keyCode = e.getKeyCode();
	        
	        switch (keyCode) {
	            case KeyEvent.VK_W:
	                System.out.println("W 키에서 손을 뗐습니다.");
	                break;
	            case KeyEvent.VK_A:
	                System.out.println("A 키에서 손을 뗐습니다.");
	                break;
	            case KeyEvent.VK_S:
	                System.out.println("S 키에서 손을 뗐습니다.");
	                break;
	            case KeyEvent.VK_D:
	                System.out.println("D 키에서 손을 뗐습니다.");
	                break;
	            default:
	                // 다른 키가 떼어졌을 때
	                break;
	        }
	    }

	    public static void main(String[] args) throws InterruptedException, IOException {
	    	Thread.sleep(1000);
	    	new MainGame();
	    }
	}

