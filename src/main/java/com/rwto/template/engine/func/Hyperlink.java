package com.rwto.template.engine.func;


import com.rwto.template.engine.exception.ErrorMsg;
import com.rwto.template.engine.exception.TemplateException;

/**
 * @author renmw
 * @create 2024/5/5 10:17
 **/
public class Hyperlink extends AbstractTemplateFunc {
    private static final String HYPER_LINK_TEMPLATE = "<a href=\"%s\"><span style=\"color: #3484ff;\">%s</span></a>";

    private Hyperlink(String name) {
        super(name);
    }

    @Override
    public String execute(Object... args) {
        if(args.length != 2){
            throw TemplateException.build(ErrorMsg.PARAMETER_ILLEGAL);
        }
        return String.format(HYPER_LINK_TEMPLATE,args[0],args[1]);
    }

    public static Hyperlink getInstance() {
        return HyperlinkHolder.INSTANCE;
    }

    private static class HyperlinkHolder{
        private static final Hyperlink INSTANCE = new Hyperlink("Hyperlink");
    }
}
