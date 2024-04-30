package Cliente.View;

import java.io.FileInputStream;

import javax.swing.JFileChooser;

public class FileChooser {
    private JFileChooser fileChooser;
    private FileInputStream fileInputStream;

    public FileChooser(){
        this.fileChooser = new JFileChooser();
    }
    public FileInputStream getProperties(){
        try{
            this.fileChooser.showOpenDialog(null);
            this.fileInputStream = new FileInputStream(this.fileChooser.getSelectedFile());
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.fileInputStream;
    }
}
