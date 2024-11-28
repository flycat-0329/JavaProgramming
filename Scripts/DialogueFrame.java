package gameCode;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//스토리 진행용 패널
class DialoguePanel extends JPanel implements ActionListener{
	final String imagePath = "C:\\Users\\song_\\eclipse-workspace\\TheEscape\\image\\";
	public Image CharacterImage;
	String[] dialogueList;
	int dialogueIndex = -1;
	JLabel textLabel;
	
	//대사 출력 UI 배치
	public DialoguePanel(String[] dialogueList)  {
		this.setLayout(null);
		this.dialogueList = dialogueList;
		setBackground(Color.BLACK);
		
		CharacterImage = new ImageIcon(imagePath + "character.png").getImage();
		
		textLabel = new JLabel();
		textLabel.setLayout(null);
		textLabel.setPreferredSize(new Dimension(460, 90));
		textLabel.setVerticalAlignment(SwingConstants.TOP);
		textLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JButton b = new JButton("다음");
		b.addActionListener(this);
		
		this.add(b);
		b.setBounds(645, 350, 60, 30);
		
		this.add(textLabel);
		textLabel.setBounds(10, 230, 690, 150);
		
		this.actionPerformed(null);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(CharacterImage, 270, 50, 150, 150, null);
	}

	
	@Override
	//확인 버튼 클릭 시 다음 대사로
	public void actionPerformed(ActionEvent e) {
		try{
			dialogueIndex++;
			textLabel.setText("<html><font color='white'>" + dialogueList[dialogueIndex] + "</font></html>");
			textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		catch(IndexOutOfBoundsException e1){
			//프롤로그라면
			if(dialogueList[0].equals("어디서부터 이렇게 되었는지도 모르겠다.")) {
				MainRoom mr = new MainRoom();
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
			}
			//엔딩이라면
			else {
				JFrame parent = (JFrame) this.getTopLevelAncestor();
				parent.dispose();
			}
		}
	}
}

public class DialogueFrame {
	public DialogueFrame(int index) {
		//대사 리스트
		String[][] dialogueList = {
			{"어디서부터 이렇게 되었는지도 모르겠다.", 
			 "처음엔 모든 일이 잘 풀리리라고 생각했다.", 
			 "곧 일자리도 다시 구하고 어머니도 금방 자리에서 털고 일어나실거라고 그렇게 생각했다.",
			 "그렇지만 나아지는건 없었다.", 
			 "조금이나마 모아둔 돈은 어머니의 병원비로 이미 다 써버렸고 내 알바비로는 밀린 이자를 메꾸기에 급급했다.",
			 "만약 내가 더 일찍 정신을 차렸더라면",
			 "만약 어머니가 아프지 않았더라면",
			 "이런 일은 벌어지지 않았을까?",
			 "그렇게 생각하던 도중 한 통의 전화가 걸려왔다.",
			 "간단한 게임에 참가하기만 하면 어머니의 병원비와 내가 진 빚을 전부 해결해주겠다는 솔깃한 제안",
			 "정신을 차리고보니 나는 그가 알려준 건물을 오르고 있었다.",
			 "내가 옳은 일을 하고 있는지는 잘 모르겠다.",
			 "다만 지금 중요한건 나는 돈이 필요하고 이 게임을 마다할 정도로 멍청한 놈은 아니라는 것이다."}, 
			{"게임이 끝났다.",
			 "마지막 문제를 풀고, 난 탈출구를 열었다.",
			 "문을 열고 밖으로 나오자, 따스한 햇살이 얼굴을 감쌌고, 차가운 바람이 피부에 스쳤다. 난 그제야 자신이 탈출했다는 것을 실감했다.",
			 "잠시 뒤, 주머니에서 진동이 느껴졌다. 휴대폰을 꺼내보니 메시지가 하나 와 있었다.",
			 "\"축하합니다. 게임을 성공적으로 완료하셨습니다. 약속대로, 원하던 모든 것을 드리겠습니다.\"",
			 "메시지와 함께 은행 계좌에 엄청난 금액이 입금되었다는 알림이 떴다. 난 한숨을 쉬며 벤치에 앉아 어머니 병원비 걱정이 사라졌음을 안도했다.",
			 "어머니는 수술 이후 금방 건강을 회복하셨고 좋은 직장도 금방 구할 수 있었다.",
			 "그러나 문득 뒤에서 느껴지는 시선에 고개를 돌렸다. 아까까지는 없던 검은 정장을 입은 낯선 남자가 바로 뒤에 서 있었다.",
			 "그는 나를 향해 희미하게 미소 지으며 말했다. \"이제 당신의 삶은 다시 당신 것이지만, 그 대가를 기억하십시오. 게임은 절대 끝나지 않습니다.\"",
			 "그 말을 끝으로 남자는 사라졌고, 난 다시 휴대폰 화면을 내려다보았다. 화면 속 메시지가 갑자기 지워지더니, 새로운 문구가 나타났다.",
			 "\"이제 당신은 선택받았습니다. 또 다른 게임이 시작될 때까지, 평온을 누리십시오.\"",
			 "난 휴대폰을 주머니에 넣으며 천천히 자리에서 일어섰다. 모든 게 끝났다고 생각했지만, 마음 한구석에서는 무언가 불길한 기운이 느껴졌다."}};
		// 틀 준비
		JFrame frame = new JFrame("스토리");
		Dimension dim = new Dimension(720, 420);
		DialoguePanel panel = new DialoguePanel(dialogueList[index]);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(dim);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		DialogueFrame intro = new DialogueFrame(0);
	}
}
