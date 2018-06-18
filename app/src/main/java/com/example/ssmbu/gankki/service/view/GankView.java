package com.example.ssmbu.gankki.service.view;

import com.example.ssmbu.gankki.service.entity.GankItem;

import java.util.List;

public interface GankView {
    void getGankComplete();
    void getGankError();

    void showGankItems(List<GankItem> gankItems);
}
