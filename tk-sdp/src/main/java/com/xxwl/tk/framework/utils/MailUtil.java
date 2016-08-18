package com.xxwl.tk.framework.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

@Component("mailUtil")
public class MailUtil{
    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    @Resource(name = "freeMarker")
    private FreeMarkerConfigurer freeMarkerConfigurer;
    // log4j
    private static Logger log = Logger.getLogger(MailUtil.class);

    /**
     * 发送普通邮件
     * @Title: sendTextMail   
     * @Description:TODO  
     * @param from 发件人
     * @param to 收件人
     * @param subject  主题
     * @param text 内容
     * @return boolean 发送成功返回true
     */
    public boolean sendTextMail(String from, String to, String subject, String text){
     
        try{
            // 创建一个纯文本邮件:
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);;
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(text);
            // 发送:
            mailSender.send(mail);
            log.info(from+" 向 "+to +"发送了一封邮件！\n主题为：\n"+subject +"\n内容为：\n"+text);
        }
        catch(MailException e){
            log.debug(e);
            return false;
        }
        return true;
    }

    /**
     * 发送html邮件
     * @Title: sendhtmlMail   
     * @Description:TODO  
     * @param from 发件人
     * @param to 收件人
     * @param subject 主题
     * @param templateFile html模板文件
     * @param map 模板变量对应的值
     * @return boolean 发送成功返回true 失败返回false
     */
    public boolean sendhtmlMail(String from, String to, String subject, String templateFile, Map<String, Object> map){
        MimeMessage message = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码，true表示为multipart邮件
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
            helper.setTo(to); // 邮件接收地址
            helper.setFrom(from); // 邮件发送地址,这里必须和xml里的邮件地址相同一致
            helper.setSubject(subject); // 主题
            helper.setText(this.getMailText(templateFile, map), true); // 邮件内容，注意加参数true，表示启用html格式
            mailSender.send(message); // 发送邮件
            log.info(from+" 向 "+to +"发送了一封邮件！\n主题为：\n"+subject);
        }
        catch(MessagingException e){
            log.debug(e);
            e.printStackTrace();
            return false;
        }catch(Exception e){
            log.debug(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /** 
     * 批量发送带附件的html格式邮件，带附件 
     */  
    public void sendBatchEmail(String from,String[] to, String subject, String templateFile, Map<String, Object> map) {  
          
        MimeMessage msg = mailSender.createMimeMessage();  
  
        try {  
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);  
            String toList = getMailList(to);  
            new InternetAddress();
            InternetAddress[] iaToList = InternetAddress.parse(toList);
            msg.setRecipients(Message.RecipientType.TO, iaToList);  
            helper.setFrom(from);  
            helper.setSubject(subject);  
            String htmlText = getMailText(templateFile, map);
            helper.setText(htmlText, true);  
            // 添加内嵌文件，第1个参数为cid标识这个文件,第2个参数为资源  
            helper.addInline("a", new File("E:/11.jpg")); // 附件内容  
            helper.addInline("b", new File("E:/12.jpg"));  
            File file = new File("E:/各种框架图介绍.docx");  
            // 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题  
            helper.addAttachment(MimeUtility.encodeWord(file.getName()), file);  
        } catch (MessagingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        // 如果主题出现乱码，可以使用该函数，也可以使用下面的函数  
        // helper.setSubject(MimeUtility.encodeText(String text,String  
        // charset,String encoding))  
        // 第2个参数表示字符集，第三个为目标编码格式。  
        // MimeUtility.encodeWord(String word,String charset,String encoding)  
        mailSender.send(msg);  
    }  
    /** 
     * 集合转换字符串 
     */  
    public String getMailList(String [] to) {
        if(to==null){
            return null;
        }
        
        
        
        return null;
    }
    
   
    // 通过模板构造邮件内容，参数content将替换模板文件中的${content}标签。
    private String getMailText(String templateFile, Map<String, Object> map) throws Exception{
        String htmlText = "";
        System.err.println(freeMarkerConfigurer.getConfiguration().getTemplateLoader());
        // 通过指定模板名获取FreeMarker模板实例
        Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templateFile);  
        // Map<String, Object> map = new HashMap<String, Object>(); 
        // FreeMarker通过Map传递动态数据
        // map.put("LOGINNAME", content); // 注意动态数据的key和模板标签中指定的属性相匹配
        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
        return htmlText;
    }

}
