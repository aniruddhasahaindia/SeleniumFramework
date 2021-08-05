package Pilot3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.poi.util.SystemOutLogger;

import ReadAndWriteInputData.Configuration;
import TestLab.TestCases;
import databaseConnection.FetchDatabase;
import databaseConnection.StoreDatabase;

public class DriverScript3 {
	
	static JFrame frame;
	static JTable table;
	static DefaultTableModel tableModel;
	static int selectedRowIndex,selectedColumnIndex;
	static int frameHeight=838,frameWidth=1550;
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//TestCases.runTestCase();
		GUITest32_1();
	}
	
	 public static void GUITest32_1(){    
			try {
				     frame = new JFrame("Zero-Code Intelligent Automator");
				     frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					 frame.setDefaultCloseOperation(3);
					 frame.setLayout(new BorderLayout());
					 
					 
					 //JButton btn_ExecuteTC = new JButton("Execute");
					 JButton btn_ExecuteTC = CreateButtonLayout("Execute");
				     JButton btn_AddRow = CreateButtonLayout("Add Row");
				     JButton btn_DeleteRow = CreateButtonLayout("Delete Row");
				     JButton btn_AddColumn = CreateButtonLayout("Add Column");
				     
				     JButton btn_TCExecutionGrid = CreateButtonLayout("Test-Case Execution Grid");
				     JButton btn_TestCaseDesign = CreateButtonLayout("Test-Case Design");
				     JButton btn_FlowDesign = CreateButtonLayout("Flow Design");
				     JButton btn_OperationConfiguration = CreateButtonLayout("Operation Configuration");
				     JButton btn_GlobalSettings = CreateButtonLayout("Global Settings");
					 
					 JPanel leftPanel=new JPanel(); 
					 	JPanel left_topPanel=new JPanel();
					 	JPanel left_bottomPanel=new JPanel();
					 JPanel rightPanel=new JPanel(); 
					 	JPanel right_topPanel = new JPanel();
					 	JPanel right_bottomPanel = new JPanel();
					 	
					 
					 //leftPanel.setBackground(Color.blue);
					 leftPanel.setLayout(new BorderLayout());
					 leftPanel.add(left_topPanel,BorderLayout.NORTH);
					 leftPanel.add(left_bottomPanel,BorderLayout.SOUTH);
					 
					 //left_topPanel.setBackground(Color.red);
					 drawTable(left_topPanel);
					 //left_topPanel.setSize(500, 500);
					 //left_topPanel.setPreferredSize(new Dimension((int) (frameWidth*.77), (int) (frameHeight*.9)));
					 table.addMouseListener(new java.awt.event.MouseAdapter() { // row is clicked
			                public void mouseClicked(java.awt.event.MouseEvent evt) {
			                    selectedRowIndex = table.getSelectedRow();
			                    selectedColumnIndex = table.getSelectedColumn();
			                    Object selectedObject = (Object) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
			                    System.out.println(selectedObject);
			                }

			            });
					 
					 
					 //left_bottomPanel.setBackground(Color.cyan);
					 left_bottomPanel.setLayout(new FlowLayout());
					 left_bottomPanel.add(btn_ExecuteTC);
					 left_bottomPanel.add(btn_AddRow);
					 left_bottomPanel.add(btn_DeleteRow);
					 left_bottomPanel.add(btn_AddColumn);
					 
					 
					 //rightPanel.setBackground(Color.green);
					 rightPanel.setLayout(new BorderLayout());
					 
					 rightPanel.add(right_topPanel,BorderLayout.NORTH);
					 	right_topPanel.setPreferredSize(new Dimension((int) (frameWidth*.23), (int) (frameHeight*.72)));
					 	System.out.println(frameWidth);
						System.out.println(frameHeight);

					 	//right_topPanel.setBackground(Color.MAGENTA);
					 	
					 rightPanel.add(right_bottomPanel,BorderLayout.SOUTH);
//					 	right_bottomPanel.setPreferredSize(new Dimension((int) (frameWidth*.23), (int) (frameHeight*.27)));
//					 	right_bottomPanel.setBackground(Color.LIGHT_GRAY);
					 	
					 	JPanel righ_bottom_centerPanel = new JPanel();
					 	//righ_bottom_centerPanel.setLayout(new BorderLayout(60, 60));
					 	right_bottomPanel.add(righ_bottom_centerPanel,BorderLayout.CENTER);
					 	//right_bottomPanel.setBackground(Color.gray);
					 	right_bottomPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
					 	right_bottomPanel.setPreferredSize(new Dimension((int) (frameWidth*.13), (int) (frameHeight*.20)));
					 	
					 	righ_bottom_centerPanel.setLayout(new GridLayout(5, 1,2,2));
					 	righ_bottom_centerPanel.add(btn_GlobalSettings);
					 	righ_bottom_centerPanel.add(btn_TCExecutionGrid);
					 	righ_bottom_centerPanel.add(btn_TestCaseDesign);
					 	righ_bottom_centerPanel.add(btn_FlowDesign);
					 	righ_bottom_centerPanel.add(btn_OperationConfiguration);
					 
					 
					 
					 btn_ExecuteTC.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {				    			 
					    			 updateExecutionGrid();
					    			 TestCases.runTestCase();					    			
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}					    			
					    	 }							 
					 });
					 
					 btn_AddRow.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {				    			 
					    			tableModel.addRow(new Object[] {"testcase id","testcase name","browser","run status"});					    			
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}					    			
					    	 }							 
					 });
					 
					 btn_DeleteRow.addActionListener(new ActionListener(){  
					    	public void actionPerformed(ActionEvent e){  
					    		try {				    			 
					    			tableModel.removeRow(selectedRowIndex);					    			
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									tableModel.removeRow(table.getColumnCount());	
								}					    			
					    	 }							 
					 }); 
					 
					 
				     frame.add(leftPanel,BorderLayout.WEST);
				     frame.add(rightPanel,BorderLayout.EAST);
					 frame.setVisible(true); 
					 					
			 }
			 catch(Exception e) {
				 System.out.println(e.getLocalizedMessage());
				 
			 }
		    
		}    

	 private static JButton CreateButtonLayout(String buttonText) {
		
		 JButton button = new JButton(buttonText);
		 button.setBackground(Color.blue);
		 button.setForeground(Color.yellow);
		 Font font = new Font("Verdana", Font.BOLD, 12);
		 button.setFont(font);
		 //button.setPreferredSize(new Dimension((int) (frameWidth*.11), 20));
		 //button.setMaximumSize(new Dimension((int) (frameWidth*.11), 20));
		 return button;
		 
	 }
	 private static void updateExecutionGrid() {	
			
		
		int iRow = table.getRowCount();
		int iCol = table.getColumnCount();
		String[] recordSet = new String[iCol];
		StoreDatabase.truncateDBValues("automation_qa", "testcases");
		for(int i=0; i<iRow; i++) {
			for(int j=0;j<iCol; j++) {
				System.out.println(table.getValueAt(i, j));
				recordSet[j] = table.getValueAt(i, j).toString();
				
			}
			System.out.println(recordSet);
			StoreDatabase.setDBValues("automation_qa", "testcases", recordSet);
		}
		 
		} 
	 
	 static String[][] formatHashMap(HashMap map)
	 	{
	 		
	 		int mapRow = map.size();
	 		int mapColumn = map.get(map.keySet().toArray()[0]).toString().split(Configuration.getDelimiter()).length+1;
	 		String[][] arr = new String[mapRow][mapColumn];
	 		for(int i = 0;i<mapRow;i++) {
	 			String key = null;
	 			int k=-1;
	 			for(int j = 0;j<mapColumn-1;j++) {
	 				k++;
	 				if(j==0) {
	 					key = map.keySet().toArray()[i].toString();
	 					arr[i][k++]=key;
	 					
	 				}
	 				arr[i][k]=map.get(key).toString().split(Configuration.getDelimiter())[j];
//	 				System.out.print("Key " + key);
//	 				System.out.println("  ... Item " + arr[i][k]);
	 			}
	 		}
	 		return arr;
	 	}
	 	 
	 private static void drawTable(JPanel tablePanel)
	 {
		 HashMap data = FetchDatabase.getDBValues("automation_qa", "testcases");
	     String[][] data1 = formatHashMap(data);
	     
	     tableModel = new DefaultTableModel();
	     table = new JTable(tableModel);
	     String column[]={"ID","NAME","DRIVER","RUN_STATUS"}; 
	     for(int i=0;i<column.length;i++) {
	    	 tableModel.addColumn(column[i]);
	     }
	     
	     for(int i=0;i<data1.length;i++) {
	    	 tableModel.addRow(data1[i]);	    	 
	     }
	     
		 JScrollPane srollPane=new JScrollPane(table); 
		 //srollPane.setMinimumSize(new Dimension(1200, 600));
		 srollPane.setPreferredSize(new Dimension((int) (frameWidth*.77), (int) (frameHeight*.9)));
		 table.getTableHeader().setBackground(Color.orange);
		 table.getTableHeader().setForeground(Color.black);
		
		 Font font = new Font("Verdana", Font.PLAIN, 15);
		 table.setFont(font);
		 
	     
		 
		 tablePanel.add(srollPane);
	 }
}
