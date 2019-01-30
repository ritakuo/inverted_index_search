import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class InvertedIndexDriver {
    public static void main(String[] args) throws Exception {
        if(args.length <2){
            System.err.println("Usage: Inverted Index <input dir> <output dir>");
            System.exit(2);
        }

        Configuration conf = new Configuration();

        conf.set("filePath", args[2]);

        Path inputDirectoryPath = new Path(args[0]);
        Path outputDirectoryPath =  new Path(args[1]);

        Job job = Job.getInstance(conf, "Inverted Index");
        job.setMapperClass(InvertedIndexMapper.class);
        job.setReducerClass(invertedIndexReducer.class);
        job.setNumReduceTasks(1); //1 output file
        job.setJarByClass(InvertedIndexDriver.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputValueClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(outputDirectoryPath)){
            fs.delete(outputDirectoryPath, true);
        }

        TextInputFormat.setInputPaths(job, inputDirectoryPath);
        TextOutputFormat.setOutputPath(job, outputDirectoryPath);


        System.exit(job.waitForCompletion(true)? 0: 1);




    }
}
