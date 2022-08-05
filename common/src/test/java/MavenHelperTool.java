import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class MavenHelperTool {
    public static void main(String[] args) throws Exception {
        listDirectory(new File("G:\\repository"));
    }

    private static void listDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            throw new IllegalArgumentException("目录: " + dir + " 不存在.");
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + "不是目录.");
        }

        File[] files = dir.listFiles();
        if (null != files && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listDirectory(file);
                } else {
                    String fileName = file.getName();
                    boolean isLastUpdated = fileName.toLowerCase().endsWith("lastupdated");
                    if (isLastUpdated) {
                        boolean isDelete = file.delete();
                        log.info("删除的文件名: {}, 删除是否成功: {}", file.getName(), isDelete);
                    }
                }
            }
        }
    }
}
