import java.io.File;
import java.util.Calendar;

//класс с вспомогательными методами, для обработки списка файлов на ftp
public class Tools {
    // private boolean flag_create_dir;
    // private String date_dir;


    //метод создания папки на локальном компьютере
    public static String createDir (){
        String dir_path = "C:/Work/";
        String[] dir_list;
        File dir_today = null;
        String date_today;
        String string_year;

        Calendar calendar = Calendar.getInstance();
        string_year = String.valueOf(calendar.get(Calendar.YEAR));
        date_today = String.valueOf(string_year.charAt(string_year.length() - 2)) + string_year.charAt(string_year.length() - 1) + "_" + Tools.addZero(calendar.get(Calendar.MONTH) + 1) + "_" + Tools.addZero(calendar.get(Calendar.DAY_OF_MONTH));
        File file = new File(dir_path);
        dir_list = file.list();

        for (int i = 0 ; i < dir_list.length; i++){
            if (dir_list[i] == date_today )
            {
                break;
            }else{
                dir_today = new File(dir_path + date_today);
                dir_today.mkdirs();
            }
        }
        return String.valueOf(dir_today);
    }

    public static String createDir(String name_dir_print) {
        File path_dir_print = null;
        String[] dir_list;
        File file = new File(createDir());
        dir_list = file.list();

        for (int i = 0; i < dir_list.length; i++) {
            if (dir_list[i] == name_dir_print)
            {
                break;
            } else {
                path_dir_print = new File(createDir() + name_dir_print);
                path_dir_print.mkdirs();
            }
        }
        return String.valueOf(path_dir_print);
    }

    public static String addZero(int number) {
        if (number < 10) {
            return String.valueOf("0" + number);
        } else {
            return String.valueOf(number);
        }
    }
}
