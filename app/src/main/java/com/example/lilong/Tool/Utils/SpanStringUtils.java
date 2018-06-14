package com.example.lilong.Tool.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import com.example.lilong.R;

import java.util.List;

/**
 * Created by long on 2018-4-12.
 */
public class SpanStringUtils {

    public static int start;
    public static int end;

    /**
     * 将传入的字符串中的某一部分转化为高亮
     * @param color 高亮的颜色
     * @param message 传入的字符串
     * @param hightValues 欲高亮的字符串
     * @return
     */
    public static SpannableString getHightLightText(int color, String message, String hightValues){
        if(TextUtils.isEmpty(message)){
            return null;
        }
        if(TextUtils.isEmpty(hightValues)){
            return null;
        }
        start = message.indexOf(hightValues);
        end = hightValues.length() + start;

        start = start > 0 ? start : 0 ;
        end = end < message.length() ? end : message.length() ;
        SpannableString spannableString = new SpannableString(message);
        spannableString.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }


    public static SpannableString getUnderline(String message, String underline){
        if(TextUtils.isEmpty(message)){
            return null;
        }
        if(TextUtils.isEmpty(underline)){
            return null;
        }
        start = message.indexOf(underline);
        end = underline.length() + start;

        start = start > 0 ? start : 0 ;
        end = end < message.length() ? end : message.length() ;
        SpannableString spannableString = new SpannableString(message);
        spannableString.setSpan(new UnderlineSpan(),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }


    public static SpannableString getURLline(String message, String phone, String phone2){
        if(TextUtils.isEmpty(message)){
            return null;
        }
        if(TextUtils.isEmpty(phone)){
            return null;
        }
        start = message.indexOf(phone);
        end = phone.length() + start;

        start = start > 0 ? start : 0 ;
        end = end < message.length() ? end : message.length() ;
        SpannableString spannableString = new SpannableString(message);
        spannableString.setSpan(new URLSpan("tel:"+phone),start,end, Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new URLSpan("tel:"+phone2),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }




    public static SpannableStringBuilder addClickablePart(final Context context, String str,
                                                          List<String> phones) {
        SpannableString spanStr = new SpannableString("");

        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(str) ;

        if (phones.size() > 0) {
            // 最后一个
            for (int i = 0; i < phones.size(); i++) {
                final String phone = phones.get(i);
                final int start = str.indexOf(phone) + spanStr.length();
                ssb.setSpan(new ClickableSpan() {

                    @Override
                    public void onClick(View widget) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + phone);
                        intent.setData(data);
                        context.startActivity(intent);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                         ds.setColor(ResUtils.getColor(R.color.common_green)); // 设置文本颜色
                        // 去掉下划线
                        ds.setUnderlineText(true);
                    }

                }, start, start + phone.length(), 0);
            }
        }
        return ssb;
    }

}
