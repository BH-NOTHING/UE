package com.ry.editor.ffmpeg2video;

import com.ry.editor.template.ueditor.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * java实现视频格式的转化
 *
 * @author king
 */
public class VideoConverter {
    private static Logger logger = LoggerFactory.getLogger(VideoConverter.class);


    //ffmpeg存放的路径
    public static String ffmpegPath = "";

    //mencoder存放的路径
    public static String mencoderPath = "C:\\Users\\KING\\Desktop\\demo\\ffmpeg-20171022-72c3d9a-win64-static\\bin\\mencoder.exe";

    //通过mencoder转换成的avi存放路径
    public static String avifilepath = "C:\\Users\\KING\\Desktop\\demo\\temp.avi";

    // 转换格式
    public static String _format = "mp4";

    /**
     * 通过一个给定的路径构建一个配置管理器
     */
    public VideoConverter() throws Exception {
        try {
            Properties prop = new Properties();
            InputStream in = ConfigManager.class.getResourceAsStream("/conf/editor.properties");
            prop.load(in);
            String _vp = prop.getProperty("ffmpeg.video.path");
            String _vf = prop.getProperty("ffmpeg.video.format");
            if (_vf != null && _vf.length() != 0) {
                this._format = _vf;
            }
            if (_vp != null && _vp.length() != 0) {
                File exe = new File(_vp);
                if (exe.exists()) {
                    this.ffmpegPath = exe.getAbsolutePath();
                } else {
                    throw new Exception("getffmpegPath异常！");
                }
            } else {
                String os = System.getProperty("os.name").toLowerCase();
                boolean isWindows;
                if (os.indexOf("windows") != -1) {
                    isWindows = true;
                } else {
                    isWindows = false;
                }
                String suffix = isWindows ? "ffmpeg.exe" : "ffmpeg";
                String _path = VideoConverter.class.getClassLoader().getResource(suffix).getPath();
                File exe = new File(_path);
                if (exe.exists()) {
                    this.ffmpegPath = exe.getAbsolutePath();
                } else {
                    throw new Exception("getffmpegPath异常！");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }


    /**
     * @param inputFile:需要转换的视频
     * @param outputFile：转换后的视频，当为“”时，默认以inputFile的文件名作为输出视频名
     * @return 转换视频文件
     */
    public String process(String inputFile, String outputFile) throws Exception {
        logger.info("视频转换开始---physicalPath:" + inputFile + "---转换格式:" + _format);
        long lon = System.currentTimeMillis();
        String returnPath = "";
        if (ffmpegPath == null || ffmpegPath.length() == 0) {
            return returnPath;
        }
        int type = checkContentType(inputFile);
        if (type == 0) {
            //throw new Exception("inputFile异常！");
            return returnPath;
        }
        if (outputFile == null || outputFile.length() == 0) {
            outputFile = inputFile.substring(0, inputFile.lastIndexOf(".") + 1) + _format;
        }


        if (type == 1) {
            returnPath = processFLV(inputFile, outputFile);// 直接将文件转为flv文件
        } else if (type == 2) {
           /* String avifilepath = processAVI(type, inputFile);
            if (avifilepath == null) {
                //throw new Exception("avi文件没有得到！");
                return boo;// avi文件没有得到
            }
            boo = processFLV(avifilepath, outputFile);// 将avi转为flv*/
        } else {
            //throw new Exception("格式无法识别！");
        }
        long lonEnd = System.currentTimeMillis();
        logger.info("视频转换结束---耗时:" + +(lonEnd - lon) + "毫秒");
        return returnPath;
    }

    /**
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
     */
    private static int checkContentType(String inputFile) {
        File file = new File(inputFile);
        if (!file.isFile()) {
            return 0;
        }
        ;
        String type = inputFile.substring(inputFile.lastIndexOf(".") + 1, inputFile.length()).toLowerCase();

        if (type.equals("avi")) {
            return 1;
        } else if (type.equals("mpg")) {
            return 1;
        } else if (type.equals("wmv")) {
            return 1;
        } else if (type.equals("3gp")) {
            return 1;
        } else if (type.equals("mov")) {
            return 1;
        } else if (type.equals("mp4")) {
            return 1;
        } else if (type.equals("asf")) {
            return 1;
        } else if (type.equals("asx")) {
            return 1;
        } else if (type.equals("flv")) {
            return 1;
        } else if (type.equals("rm")) {
            return 2;
        } else if (type.equals("rmvb")) {
            return 2;
        } else if (type.equals("wmv9")) {
            return 2;
        }
        return 3;
    }


    /**
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）直接转换为目标视频
     */
    private static String processFLV(String inputFile, String outputFile) {

        List<String> commend = new ArrayList<String>();

        commend.add(ffmpegPath);
        commend.add("-y");
        commend.add("-i");
        commend.add(inputFile);
        commend.add("-ar");
        commend.add("44100");

        commend.add("-vcodec");
        commend.add("libx264");

        commend.add(outputFile);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < commend.size(); i++) {
            stringBuffer.append(commend.get(i) + " ");
        }

        logger.info("processFLV---runCmd:" + stringBuffer.toString());

        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process p = builder.start();
            processWait(p);
            return outputFile;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    /**
     * 保持进程结束
     */
    private static void processWait(Process p) throws Exception {

        final InputStream is1 = p.getInputStream();
        final InputStream is2 = p.getErrorStream();
        new Thread() {
            public void run() {
                BufferedReader br = new BufferedReader(new InputStreamReader(is1));
                try {
                    String lineB = null;
                    while ((lineB = br.readLine()) != null) {
                        if (lineB != null) {
                            logger.info(lineB);
                        }
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }.start();
        new Thread() {
            public void run() {
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                try {
                    String lineC = null;
                    while ((lineC = br2.readLine()) != null) {
                        if (lineC != null) {
                            logger.info(lineC);
                        }
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }.start();

        // 等进程转换结束，再调用ffmepg进程
        p.waitFor();
    }

    /**
     * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
     * 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
     */
    private static String processAVI(int type, String inputFile) {
        File file = new File(avifilepath);
        if (file.exists()) {
            file.delete();
        }
        List<String> commend = new ArrayList<String>();
        commend.add(mencoderPath);
        commend.add(inputFile);
        commend.add("-oac");
        commend.add("mp3lame");
        commend.add("-lameopts");
        commend.add("preset=64");
        commend.add("-ovc");
        commend.add("xvid");
        commend.add("-xvidencopts");
        commend.add("bitrate=600");
        commend.add("-of");
        commend.add("avi");
        commend.add("-o");

        commend.add(avifilepath);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < commend.size(); i++) {
            stringBuffer.append(commend.get(i) + " ");
        }

        logger.info("processAVI---runCmd:" + stringBuffer.toString());
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process p = builder.start();
            processWait(p);
            return avifilepath;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        long lon = System.currentTimeMillis();
        String inputStr = "C:\\Users\\KING\\Desktop\\demo\\1005485_20161117_134110_0000.avi";
        String outputStr = "C:\\Users\\KING\\Desktop\\demo\\target.flv";
        try {
            String returnPath = new VideoConverter().process(inputStr, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long lonEnd = System.currentTimeMillis();
        System.out.println("原时间戳和当前时间戳中间相隔:" + (lonEnd - lon) + "毫秒");
        System.out.println("转换结束");
    }
}
