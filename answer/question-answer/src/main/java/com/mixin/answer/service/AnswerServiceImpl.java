package com.mixin.answer.service;

import com.mixin.answer.api.IAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @description: 答题
 * @author: li baojian
 * @create: 2019/12/02 13:54
 */
@Service
public class AnswerServiceImpl implements IAnswerService {
    @Override
    public String getAnswer(String question) {
        if(StringUtils.isEmpty(question)){
            return "请输入问题！";
        }
        if(!question.contains("？")&&!question.contains("[A]")){
            return "输入的问题类型错误";
        }
        HashMap<String, ArrayList> map = getResource();
        String answer = getAnswer(map, question);
        if(answer==null){
            return "该题未输入题库，请通知管理员输入该题！";
        }
        answer = answer.substring(answer.indexOf("[") + 1, answer.indexOf("]"));
        return answer;
    }

    public HashMap<String, ArrayList> getResource() {
        HashMap<String, ArrayList> map = new HashMap<>();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("text.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String question = null;
            String s1 = null;
            while ((s1 = bufferedReader.readLine()) != null) {
                ArrayList<String> answers = new ArrayList<>();
                s1 = s1.replace(" ", "");
                if (StringUtils.isEmpty(s1)) {
                    continue;
                }
                if (s1.contains("？") || s1.contains("?")) {
                    question = s1;
                    map.put(s1, answers);
                } else if (!StringUtils.isEmpty(question) && !question.equals("")) {
                    map.get(question).add(s1);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private String getAnswer(HashMap<String, ArrayList> map, String question) {
        ArrayList list = getQuestionMap(question);
        if (question.contains("?") || question.contains("？")) {
            int l = question.indexOf("？") == 0 ? question.indexOf("?") : question.indexOf("？");
            String str = question.substring(5, l);
            str = str.replace(" ", "");
            for (String key : map.keySet()) {
                if (key.contains(str)) {
                    String s1 = (String) map.get(key).stream().filter(s -> s.toString().contains("√")).findFirst().get();
                    s1 = s1.substring(1, s1.indexOf("√"));
                    final String ss = s1;
                    String s2 = (String) list.stream().filter(s -> s.toString().contains(ss)).findFirst().get();
                    return s2;
                }
            }
        }
        return null;
    }

    private ArrayList getQuestionMap(String question) {
        HashMap<String, ArrayList> map = new HashMap<>();
        ArrayList<String> answers = new ArrayList<>();
        ArrayList<String> split = splitByChar(question);
        for (String str : split) {
            if (StringUtils.isEmpty(str.replace(" ", ""))) {
                continue;
            }
            if (str.contains("?") || str.contains("？")) {
                map.put(str, answers);
            } else {
                answers.add(str.replace(" ", ""));
            }
        }
        return answers;
    }

    private ArrayList<String> splitByChar(String a) {
        String question = a.substring(0, a.indexOf("[A]")).trim().replace(" ", "");
        String A = a.substring(a.indexOf("[A]"), a.indexOf("[B]")).trim().replace(" ", "");
        String B = a.substring(a.indexOf("[B]"), a.indexOf("[C]")).trim().replace(" ", "");
        String C = a.substring(a.indexOf("[C]"), a.indexOf("[D]")).trim().replace(" ", "");
        String D = a.substring(a.indexOf("[D]"), a.indexOf("请点击")).trim().replace(" ", "");
        ArrayList<String> list = new ArrayList<>();
        list.add(question);
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        return list;
    }
}
