import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import ReadAndWriteInputData.Configuration;
import databaseConnection.FetchDatabase;

public class GUITest32 {
	static JFrame f;    
	public static void main(String[] args)
	{
		GUITest32_1();
	}
	 public static void GUITest32_1(){    
		try {
			    f=new JFrame();  
			    f.getContentPane().setBackground(Color.green);
			     HashMap data = FetchDatabase.getDBValues("automation_qa", "testcases");
			     String[][] data1 = formatHashMap(data);
			     System.out.println(data1[0].length);
			    //System.out.println(data1);
			    //System.out.println(data1);
			    /*String data1[][]={ {"101","Amit","670000"},    
			                          {"102","Jai","780000"},    
			                          {"101","Sachin","700000"}};   */
			    String column[]={"ID","NAME","DRIVER","TESTCASE_STATUS"};  
			    //TableModel model = new EditableTableModel(data1,column);
			    JTable jt=new JTable(data1,column);   
			    jt.setBounds(30,40,200,300);          
			    JScrollPane sp=new JScrollPane(jt);  
			    jt.getTableHeader().setBackground(Color.orange);
			    jt.getTableHeader().setForeground(Color.black);
			    Font font = new Font("Verdana", Font.PLAIN, 15);
			    jt.setFont(font);
			    JButton btn = new JButton("Execute!");
			    btn.setBounds(250, 150, 150, 30);
			    f.add(btn);
			    btn.addActionListener(new ActionListener(){  
			    	public void actionPerformed(ActionEvent e){  
			    		    
			    		 try {
			    			 System.out.println("Welcome to Javatpoint 1");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    		 //UiTest21();
			    		 System.out.println("Welcome to Javatpoint 2");
			    	        }  
			    	    });  
			    
			    f.add(sp);          
			    f.setSize(500,400);  
			  
			    f.setVisible(true); 
			    f.setExtendedState(JFrame.MAXIMIZED_BOTH);
			    f.setDefaultCloseOperation(3);
		 }
		 catch(Exception e) {
			 System.out.println(e.getLocalizedMessage());
			 
		 }
	    
	}    
	 
	 public  void UiTest21() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
					System.out.println("Hello World!!");
					
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\aniru\\OneDrive\\My Work\\Automation\\selenium Java\\chromedriver.exe");
					
					ChromeDriver driver = new ChromeDriver();
					driver.get("https://www.calculator.net/");
					
					driver.findElement(By.xpath("//a[text()='Interest Calculator']")).click();
					
					
					driver.findElement(By.xpath("//input[@id='cstartingprinciple']")).sendKeys(Integer.toString(Float.floatToRawIntBits((float) (Math.random()*10000))));
					driver.findElement(By.xpath("//input[@id='cstartingprinciple']")).clear();				
					driver.findElement(By.xpath("//input[@id='cstartingprinciple']")).sendKeys("2000");
					driver.findElement(By.xpath("//input[@value='Calculate']")).click();
					System.out.print("Final output by autoted script is : ");
					System.out.println(driver.findElement(By.xpath("//*[@id=\"content\"]/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]")).getText());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					driver.close();
					driver.quit();

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
	 				System.out.print("Key " + key);
	 				System.out.println("  ... Item " + arr[i][k]);
	 			}
	 		}
	 		return arr;
	 	}
	  

}
