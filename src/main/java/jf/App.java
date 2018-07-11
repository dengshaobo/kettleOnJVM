package jf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import java.util.UUID;



//import org.apache.commons.cli.CommandLineParser;
//import org.apache.commons.cli.BasicParser;
//import org.apache.commons.cli.Options;
//import org.apache.commons.cli.CommandLine;



/**
 * Hello world!
 *
 */
public class App 
{

    private final static Logger logger_info = LoggerFactory.getLogger(App.class);
    private final static Logger logger_error = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {


        logger_info.debug("[================debug===============]");

        System.out.println("start!");


        // Create a Parser
//        CommandLineParser parser = new BasicParser( );
//        Options options = new Options( );
//        options.addOption("h", "help", false, "Print this usage information");
//        options.addOption("v", "verbose", false, "Print out VERBOSE information" );
//        options.addOption("f", "file", true, "File to save program output to");
        // Parse the program arguments
//        CommandLine commandLine = parser.parse( options, args );

        System.out.println("eeee");


//        System.out.println("start------------------------");
//        App.runKettleTransfer("/Users/fenghuacaijing/Documents/test_news.ktr");
//        System.out.println("end one ----------");


        System.out.println("end");
    }

    public static boolean runKettleTransfer( String ktrFilePath) {

        Trans trans = null;

        String uuid = UUID.randomUUID().toString();

        logger_info.info("ExecKettleUtil@runKettleTransfer:"+uuid+" {ktrFilePath:"+ktrFilePath+"}");
        try {
            //初始化
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            TransMeta transMeta = new TransMeta(ktrFilePath);
            //转换
            trans = new Trans(transMeta);

            //执行转换
            trans.execute(null);
            //等待转换执行结束
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                logger_info.info("ExecKettleUtil@runKettleTransfer:"+uuid+" 执行失败");
            }else{
                logger_info.info("ExecKettleUtil@runKettleTransfer:"+uuid+" 执行成功");
            }
            return true;
        } catch (Exception e) {
            logger_error.error("ExecKettleUtil@runKettleTransfer:"+uuid, e);
            return false;
        }
    }
}
