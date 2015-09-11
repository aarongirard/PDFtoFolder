
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PDFGUI {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel1;
	private JPanel controlPanel3;

	private File inputFile;
	private File outputFile;

	public PDFGUI()
	{
		prepareGUI();
	}

	public static void main(String[] args) 
	{
		PDFGUI autoLocateGUI = new PDFGUI();
 		autoLocateGUI.showChoice();
	}

	private void prepareGUI()
	{
		//initialize mainframe
		mainFrame = new JFrame("PDF Folder Automater");
		//set its size
		mainFrame.setSize(400,400);
		//set the layout as 3 rows by 1 col
		mainFrame.setLayout(new GridLayout(4, 1));
		//if window is closed, exit program
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		headerLabel = new JLabel("", JLabel.CENTER);


      	statusLabel = new JLabel("", JLabel.CENTER);        
     	statusLabel.setSize(350,100);

     	

     	controlPanel1 = new JPanel();
     	controlPanel1.setLayout(new FlowLayout());
     	controlPanel3 = new JPanel();
     	controlPanel3.setLayout(new FlowLayout());

     	mainFrame.add(headerLabel); //layout at 1
	    mainFrame.add(controlPanel1); //layout at 2
	    mainFrame.add(controlPanel3); //layout 5
	    mainFrame.add(statusLabel); //layout at 6
	    mainFrame.setVisible(true);
	}

	private void showChoice()
	{
		//set text explaining choice
		headerLabel.setText("1) Where is your folder contain the PDF's? (choose a folder)");
		//object which represents the choice dropdown menu
		//final Choice locatorChoice = new Choice();
		//add the choices
		//locatorChoice.add("Inmate Locator");
		//locatorChoice.add("Representative Locator");

		JButton button1 = new JButton("Open a folder");
		JFileChooser fc1 = new JFileChooser();
		fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int returnVal = fc1.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
            		 inputFile = fc1.getSelectedFile();
            		//System.out.println(inputFile);
            	}
			}
		});

		//controlPanel1.add(locatorChoice);
		controlPanel1.add(button1);
		
		
		//button to execute command
		JButton button3 = new JButton("Go!");
		
		button3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//set status
				statusLabel.setText("I'm Working Here...");
				
				//error, need files
				if(inputFile == null){
					System.exit(0); //error message needs to be added
				}
				
				//run the PDF
				pdfIntoFolder pdfIntoFolder = new pdfIntoFolder(inputFile.toString());
				try {
					pdfIntoFolder.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					statusLabel.setText("THERE WAS A SERIOUS ERROR!!!!!!!!!!!!!!!!!");
				}
				
				statusLabel.setText("Done");
			}
		});
		
		controlPanel3.add(button3);
		
	}
}
