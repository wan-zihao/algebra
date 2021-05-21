package com.feather.algebraback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class AlgebraBackApplicationTests {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

    private static ThreadFactory threadFactory = (@NonNull final Runnable r) -> new Thread(() -> r.run());

    @Value("${spring.mail.username}")
    private String form;

    @Autowired
    private JavaMailSenderImpl mailSender;

    private String emailServiceCode;

    @Test
    public void test() {
        emailServiceCode = "1234";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("注册验证码");
        message.setText("注册验证码是：" + emailServiceCode);
        message.setFrom(form);
        message.setTo("2413678497@qq.com");
        mailSender.send(message);
        System.out.println("000000");
    }

    @Test
    public void ThreadTest(){
        scheduledExecutorService.schedule(threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("芜湖");
            }
        }), 3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        System.out.println("111");
        scheduledExecutorService.schedule(threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("芜湖");
            }
        }), 120, TimeUnit.SECONDS);
    }

}
