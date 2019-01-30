import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

//input: <keyword, <doc1, doc1, doc1, doc2, doc2, doc2, doc2>>
//output: <keyword, doc1\ndoc2>
public class invertedIndexReducer extends Reducer<Text,Text,Text,Text> {

    public void reduce(final Text key, final Iterable<Text> books, final Context context) throws IOException, InterruptedException {
        int minCount= 100; // to qualify to be a keyword, need to happen 100 times or more in one book
        StringBuilder sb = new StringBuilder();
        String prevBook = null;
        int count=0; //number of time the keyword appear in this book

        //go through the value of each book
        for (Text book: books){
            if(prevBook != null && book.toString().trim().equals(prevBook)){
                count++;
                continue;
            }
            //if curbook != prevbook, then prevbook is finished counting, check if keyword for prevbook>100, if not, reset counter
            if(prevBook != null && count < minCount){
                count=1;
                prevBook=book.toString().trim();
                continue;

            }
            if (prevBook == null){
                prevBook = book.toString().trim();
                count++;
                continue;

            }
            //enter here when prevBook != null && count > minCount
            sb.append(prevBook);
            sb.append("\t");
            count=1;
            prevBook=book.toString().trim();
        }
        //adding the last book
        if (count >= minCount){
            sb.append(prevBook);
        }
        if(!sb.toString().trim().equals("")){
            context.write(key, new Text(sb.toString()));
        }

    }

}
