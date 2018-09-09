package net.ogalab.cuidemo.dao;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultInfo {


    String line = null;

    String seqid = null;
    String source = null;
    String type   = null;

    long   start = 0L;
    long   end   = 0L;

    String jbrowseUrl = "";

    String score;
    String strand;
    String phase;
    String attributes;


    public ResultInfo() { }

    public ResultInfo(String line) {
        parse(line);
    }

    public boolean isNull() {
        if (this.line == null) {
            return true;
        }
        else {
            return false;
        }
    }


    Pattern p = Pattern.compile("^(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(.+)");

    public void parse(String gff3_line) {

        Matcher m = p.matcher(gff3_line);

        //this.line  = new String(gff3_line);

        //String[] cols = gff3_line.split("\\s+");

        if (m.find()) {

            this.seqid = m.group(1);
            this.source = m.group(2);
            this.type = m.group(3);
            this.start = Long.valueOf(m.group(4));
            this.end = Long.valueOf(m.group(5));
            this.score = m.group(6);
            this.strand = m.group(7);
            this.phase = m.group(8);
            this.attributes = m.group(9);

            this.line = gff3_line;

            generateJBrowseUrl();
        }
    }


    public void generateJBrowseUrl() {

        StringBuilder builder = new StringBuilder();
        StringBuilder value   = null;

        try {
            builder.append("http://rgm01.nig.ac.jp:60020/jbrowse/JBrowse-1.14.2-dev/index.html?data=sample_data/json/volvox");

            builder.append("&loc=");
            value = new StringBuilder();
            value.append("\"" + seqid + "\":");
            value.append(String.valueOf(start));
            value.append("..");
            value.append(String.valueOf(end));
            //System.out.println(value.toString());
            builder.append(URLEncoder.encode(value.toString(), "UTF-8"));


            builder.append("&addFeatures=");
            value = new StringBuilder();
            value.append("[{");
            value.append("\"seq_id\":\"" + seqid + "\",");
            value.append("\"start\":" + String.valueOf(start) + ",");
            value.append("\"end\":" + String.valueOf(end) + ",");
            value.append("\"type\":" + "\"match\"" + ",");
            value.append("\"subfeatures\":[{");
            value.append("\"start\":" + String.valueOf(start) + ",");
            value.append("\"end\":" + String.valueOf(end) + ",");
            value.append("\"type\":" + "\"match_part\"" + "}]}]");
            //System.out.println(value.toString());
            builder.append(URLEncoder.encode(value.toString(), "UTF-8"));


            builder.append("&addTracks=");
            value = new StringBuilder();
            value.append("[{\"label\":\"textsearch\",\"key\":\"text search hits\",");
            value.append("\"type\":\"JBrowse/View/Track/CanvasFeatures\",");
            value.append("\"store\":\"url\",\"glyph\":\"JBrowse/View/FeatureGlyph/Segments\"}]");
            //System.out.println(value.toString());
            builder.append(URLEncoder.encode(value.toString(), "UTF-8"));


            builder.append("&tracks=textsearch");
            builder.append("&highlight=");
            value = new StringBuilder();
            value.append(seqid + ":"
                + String.valueOf(start)
                + ".."
                + String.valueOf(end));
            //System.out.println(value.toString());
            builder.append(URLEncoder.encode(value.toString(), "UTF-8"));

            this.jbrowseUrl = builder.toString();//URLEncoder.encode(builder.toString(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }






    public String getSeqid() {
        return seqid;
    }

    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }



    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }



    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }


    public String getJbrowseUrl() {
        return jbrowseUrl;
    }

    public void setJbrowseUrl(String url) {
        this.jbrowseUrl = url;
    }




}
