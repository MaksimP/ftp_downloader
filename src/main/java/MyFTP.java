import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.io.CopyStreamException;

import java.io.FileOutputStream;
import java.io.IOException;

//работа с ftp

public class MyFTP {

    private String password = "ghbdtn";
    private String ip_address = "91.201.177.98";
    private String name_user = "ForTrueFlow";
    private String core_dir_ftp = "//";
    private int port = 21;

    public FTPClient connectToFTP(String address, int port, String username, String password)
            throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(address, port);
        ftpClient.login(username, password);

        return ftpClient;
    }

    public FTPFile[] getListOfFile(String path) throws IOException
    {
        FTPClient ftpClient = connectToFTP(ip_address, port, name_user , password);
        FTPFile[] listFtpFile = ftpClient.listFiles(path);
      /*  for (FTPFile ftpFile1 : listFtpFile) {
            System.out.println("Name - " + ftpFile1.getName().toString() +
                    " Time - " + ftpFile1.getSize());
        }      */
        ftpClient.logout();
        ftpClient.disconnect();
        return listFtpFile;
    }

 /*   public FTPFile[] getListOfFile(String dir_ftp) throws IOException{

    }      */

    public boolean downloadFromFTP(String path, String fileName, String dirName) throws IOException {

        FTPClient ftpClient = connectToFTP(ip_address, port, name_user, password);
        FileOutputStream fileOutputStream = new FileOutputStream(Tools.createDir(dirName) + fileName);
        boolean isDownload = ftpClient.retrieveFile(path + fileName, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        ftpClient.logout();
        ftpClient.disconnect();
        return isDownload;
    }

    public boolean changeDirFtp(String directory) throws IOException {
        FTPClient ftpClient = connectToFTP(ip_address, port, name_user, password);
        boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(directory);
        return changeWorkingDirectory;
    }
}
