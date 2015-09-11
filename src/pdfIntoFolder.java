
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;




public class pdfIntoFolder
{	
	public String fileContainingPDFName;
	//public String outputFileName;
	
	public pdfIntoFolder(String fileContainingPDFName)
	{
		this.fileContainingPDFName = fileContainingPDFName;
		System.out.println(fileContainingPDFName);
		
	}
	
	
	
	
	public void run() throws IOException
	{
		File fileContaingingPDF;
		File[] fileContainingPDFs; //array of abstract pathnames
		File outputFile;
		
		//get directory of folder containg all pdf’s
		fileContaingingPDF = new File(this.fileContainingPDFName);
		
									  
		
		//create new folder in desktop for output
		outputFile = new File(fileContaingingPDF.toPath() + "/FoldersWithPDFs");
		outputFile.mkdir();
		
		//get abstract pathanmes of PDFs
		fileContainingPDFs = fileContaingingPDF.listFiles();

		//iterate through each pdf in the folder
		for(File file: fileContainingPDFs)
		{
			String[] fileNameCDCR;
			
			//get name of file and get key: lastName, FirstName_CDCR#
			fileNameCDCR = getNameForFolderFromPDF(file.toString());
			
			//if file is not a pdf we are looking for than skip it.
			if(fileNameCDCR[0] == "-1"){
				continue;
			}
			
			/*for(String string: fileNameCDCR){
				System.out.println(string);
			}*/
			//create name for the would be folder for this pdf file
			String nameOfDir = outputFile.toString() + "/" + fileNameCDCR[0] + "_" + fileNameCDCR[1]; 
			File newDirForPDF = new File(nameOfDir);
			
			//name for pdf in new file
			String targetDestFileName;
			
			//need just the name of file, not abstract clas path
			String[] fileNames = file.toString().split("/");
			String fileName = fileNames[fileNames.length-1];
			
			//name of directory + pdf file name
			targetDestFileName = nameOfDir + "/" + fileName;
			System.out.println(targetDestFileName);
			
			File targetDestinationForFile = new File(targetDestFileName);
			
			
			if(newDirForPDF.exists()){
				//then add pdf to file
				//Copy(source, target)
				Files.copy(file.toPath(), targetDestinationForFile.toPath());
				
				
			} else{
				//then create new folder
				//add pdf to new folder
				newDirForPDF.mkdir();
				Files.copy(file.toPath(), targetDestinationForFile.toPath());
				
			}
			
				
			
			//create a new folder within output folder with name from pdf 	
			/*String newDirName = outputFile.toString() + "/" + getNameForFolderFromPDF(file.toString());
			File newDir = new File(newDirName);
			
			//file to copy file to
			String[] fileNames = file.toString().split("/");
			String fileName = fileNames[fileNames.length-1];
			File targetDestinationForFile = new File(newDir.toString()+"/"+fileName);
			//copy pdf into folder
			//Copy(source, target)
			
			Files.copy(file.toPath(), targetDestinationForFile.toPath());*/
		}
	}
	public static String[] getNameForFolderFromPDF(String string)
	{
		String fileName;
		String[] nameCDCRArray;
		
		//gets full pathname, just went file/folder name
		String[] strings = string.split("/");
		fileName = strings[strings.length-1];
		
		//some strings have excess info, just want anything before a '_'
		if(fileName.contains("-")){
			String[] strings1 = fileName.split("_");
			fileName = strings1[0];
		}
		nameCDCRArray = fileName.split("-");
		
		//if file not a pdf we are looking for than skip it
		if(nameCDCRArray.length == 1){
			String[] ret =  {"-1"};
			return ret;
		};
		
		//remove .pdf on some files
		if(nameCDCRArray[1].contains(".pdf")){
			nameCDCRArray[1] = nameCDCRArray[1].substring(0,(nameCDCRArray[1].length() - 4) );
		}
		return nameCDCRArray;
		
	}
}
