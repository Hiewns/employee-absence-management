package mytag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToVietnameseDateTag extends SimpleTagSupport {

    private Date value;

    public void setValue(Date value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (value != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
            String[] parts = df.format(value).split(" ");
            String formatted = "Ngày " + parts[0] + " tháng " + parts[1] + " năm " + parts[2];
            JspWriter out = getJspContext().getOut();
            out.print(formatted);
        }
    }
}
