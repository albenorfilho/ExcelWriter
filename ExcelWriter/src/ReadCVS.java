import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.LogStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class ReadCVS {

	private String csvFile;
	
  public static void main(String[] args) {
	
	String csvFile = "C:\\InputFile.log";
	ReadCVS obj = new ReadCVS(csvFile);
	obj.run();
 
  }
 
  public ReadCVS(String csvFile){
	  this.csvFile = csvFile;
  }
  
  public void run() {
	BufferedReader br = null;
	String line="";
	String cvsSplitBy = "%";
	
	try {
 
		br = new BufferedReader(new FileReader(csvFile));
		ArrayList<String> fileFields = new ArrayList<String>(); 
		ArrayList<ArrayList<String>> allValues = new ArrayList<ArrayList<String>>();
		fileFields = getFields();
		int cont = 0;
		
		while ((line = br.readLine()) != null) {
			ArrayList<String> lineFields = new ArrayList<String>();
			ArrayList<String> lineValues = new ArrayList<String>(); 
			StringTokenizer token = new StringTokenizer(line, cvsSplitBy);
			
			if(line.contains("SKILLTEST")==false){
				cont++;
			}else{			
			while(token.hasMoreElements() ){
				
				String prefield = token.nextToken();
				String field, fieldValue;
					
					if(prefield.contains("EVENT")){ //This is for remove the log line
						field = "EVENT";
						fieldValue = token.nextToken();
					}else{
						field = prefield;
						fieldValue = token.nextToken();
					}				
						
				lineValues.add(fieldValue);	
		
				}
			}		
			if(lineValues.isEmpty()==false){
			allValues.add(lineValues);			
			}
			
 
		}
		
		ExcelFactory newFile = new ExcelFactory();
		newFile.expExcel("output.xls", fileFields, allValues); //For create the file
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }
  
  public ArrayList<String> getFields(){ //This method is just to populate the fields arraylist
		BufferedReader br = null;
		String line="";
		String cvsSplitBy = "%";
		ArrayList<String> fileFields = new ArrayList<String>(); 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
		
			while ((line = br.readLine()) != null) {
				
				ArrayList<String> lineValues = new ArrayList<String>(); 
				ArrayList<String> lineFields = new ArrayList<String>();
				String values="";	//to get all the value fields of the line in a string
				StringTokenizer token = new StringTokenizer(line, cvsSplitBy);
				
				if(line.contains("SKILLTEST")==false){
					
				}else{
				
				while(token.hasMoreElements() ){
					
					String prefield = token.nextToken();
					String field, fieldValue;
						
						if(prefield.contains("EVENT")){ //This is for remove the log line
							field = "EVENT";
							fieldValue = token.nextToken();
						}else{
							field = prefield;
							fieldValue = token.nextToken();
						}
	
					lineFields.add(field);
							if(fileFields.contains(field)==false){
							fileFields.add(field);
							}
							
					lineValues.add(fieldValue);	

					}
				}
	
			}
			
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileFields;
		
  }
  
 
}

//skip