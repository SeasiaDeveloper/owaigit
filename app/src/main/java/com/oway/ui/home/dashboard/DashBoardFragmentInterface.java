package com.oway.ui.home.dashboard;

import com.oway.base.MvpView;
import com.oway.model.response.GetSaldoResponse;

public interface DashBoardFragmentInterface extends MvpView {
    void onGetSaldoResponseSuccess(GetSaldoResponse status);
    void onGetsaldoResponseFailure(String response);
}
