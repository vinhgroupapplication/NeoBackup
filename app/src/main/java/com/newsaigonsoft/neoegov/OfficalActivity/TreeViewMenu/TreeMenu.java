package com.newsaigonsoft.neoegov.OfficalActivity.TreeViewMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.OfficalActivity.BaseOffical;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.HeaderMenu;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.SumManager;
import com.unnamed.b.atv.model.TreeNode;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.setMargins;

public class TreeMenu extends TreeNode.BaseNodeViewHolder<TreeMenu.MenuTreeItem> implements View.OnClickListener {

    ImageView menuIcon;
    TextView menuName;
    TextView menuNumber;
    ImageView menuRight;
    RelativeLayout menuItem;
    TextView menuBottomTitle;
    View menuLine;
    String Title;
    LinearLayout lnChangePosition, lnChangeProfile, lnChangePass;
    TextView tvLogOut;
    SumManager manager = new SumManager();


    public TreeMenu(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, final MenuTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view;
        if (value.getMenuName().equals("AcountMenu")) {
            view = inflater.inflate(R.layout.custom_list_slider_menu_header_account, null, false);
            lnChangePosition = (LinearLayout) view.findViewById(R.id.change_position);
            lnChangePosition.setOnClickListener(this);
            lnChangeProfile = (LinearLayout) view.findViewById(R.id.change_profile);
            lnChangeProfile.setOnClickListener(this);
            lnChangePass = (LinearLayout) view.findViewById(R.id.change_pass);
            lnChangePass.setOnClickListener(this);
            tvLogOut = (TextView) view.findViewById(R.id.log_out);
            tvLogOut.setOnClickListener(this);
        } else {
            view = inflater.inflate(R.layout.custom_list_slider_menu_header, null, false);
        }
        menuItem = (RelativeLayout) view.findViewById(R.id.linear_item_header);
        menuBottomTitle = (TextView) view.findViewById(R.id.bottom_title);
        menuLine = view.findViewById(R.id.line_under);
        menuIcon = (ImageView) view.findViewById(R.id.icon_menu);
        menuName = (TextView) view.findViewById(R.id.menu_name);
        menuNumber = (TextView) view.findViewById(R.id.number_lable_count_header);
        menuRight = (ImageView) view.findViewById(R.id.right_icon);
        menuIcon.setImageResource(value.getMenuIcon());
        menuName.setText(value.getMenuName());
        menuName.setTextColor(Color.parseColor(value.getTextColor()));
        menuNumber.setText(String.valueOf(value.getHeaderCount()));
        menuNumber.setVisibility(value.getHeaderCount() != 0 ? View.VISIBLE : View.GONE);
        if (value.getMenuName().equals(nULL_STRING)) {
            menuItem.setVisibility(View.GONE);
        } else {
            if (value.getMenuName().equals("AcountMenu")) {
                menuItem.setVisibility(View.GONE);
            } else {
                menuItem.setVisibility(View.VISIBLE);
            }
        }
        menuBottomTitle.setVisibility(value.getMenuName().equals(nULL_STRING) ? View.VISIBLE : View.GONE);
        menuLine.setVisibility(value.getMenuName().equals(nULL_STRING) ? View.GONE : View.VISIBLE);
        if (value.isBoldText()) menuName.setTypeface(null, Typeface.NORMAL);
        if (value.isChangeArrowRightToLeft()) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) menuRight.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            menuRight.setLayoutParams(params);
            setMargins(menuRight, 25, 0, 0, 0);
        }
        if (node.getLevel() == 3)
            menuName.setTextAppearance(context, R.style.textsizeChildMenuName);
        if (node.getLevel() == 2) {
            manager.setBGColorDrawable(menuNumber, "#72C9ED");
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) menuNumber.getLayoutParams();
            params.removeRule(RelativeLayout.RIGHT_OF);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            menuNumber.setLayoutParams(params);
            setMargins(menuNumber, 0, 0, 30, 0);
        } else {
            manager.setBGColorDrawable(menuNumber, "#637F95");
        }
        menuNumber.setTextAppearance(context, R.style.textsizeChildMenuCount);
        if (value.getHeightLine() == 5) setMargins(menuLine, 0, 0, 0, 0);
        menuRight.setVisibility(value.isShowArrowRight() ? View.VISIBLE : View.GONE);
        menuRight.setVisibility(node.getChildren().size() != 0 ? View.VISIBLE : View.GONE);
        menuLine.getLayoutParams().height = value.getHeightLine();
        if (BaseOffical.lastClickNode != null &&
                value.getMenuCode().equals(BaseOffical.lastClickNode)
                && value.getMenuName().equals(BaseOffical.lastClickNodeName)
                ) {
            menuName.setTextColor(Color.parseColor("#5496DB"));
        }
        if (!value.getMenuCode().equals("config_user") && node.getChildren().size()!=0){
            node.setExpanded(true);
        }
        menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (node.getChildren().size() != 0) {
//                    tView.toggleNode(node);
                } else {
                    MenuTreeItem parentNodeValues = (MenuTreeItem) node.getParent().getValue();
                    if (node.getLevel() == 2 || node.getLevel() == 3) {
                        Title =  parentNodeValues.getMenuName()  + " " +  value.getMenuName();
                    } else {
                        Title = value.getMenuName();
                    }
                    ((OfficalActivity) context).MenuClick(value.MenuCode, Title);
                }
                expandCurrentandCloseOtherNode(node);
                forcusNodeClicked(node);
                BaseOffical.saveState = tView.getSaveState();
                BaseOffical.lastClickNode = value.getMenuCode();
                BaseOffical.lastClickNodeName = value.getMenuName();
            }
        });
        return view;
    }

    private void forcusNodeClicked(TreeNode node) {
        for (TreeNode n : node.getParent().getChildren()) {
            if (node == n) {
                TreeMenu adapter = (TreeMenu) n.getViewHolder();
                adapter.setSelected();
                // unhightline parent add start
                if (node.getLevel() == 2 || node.getLevel() == 3) {
                    TreeNode parentNode = node.getParent();
                    TreeMenu adapterParent = (TreeMenu) parentNode.getViewHolder();
                    adapterParent.setUnSelected();
                }
                //unhightline parent add end
            } else {
                if (n.getChildren().size() != 0) {
                    for (TreeNode c : n.getChildren()) {
                        TreeMenu adapterC = (TreeMenu) c.getViewHolder();
                        adapterC.setUnSelected();
                    }
                }
                TreeMenu adapter = (TreeMenu) n.getViewHolder();
                adapter.setUnSelected();
            }
        }
    }


    @SuppressLint("ResourceAsColor")
    public TextView setSelected() {
        if (menuName != null) {
            menuName.setTextColor(Color.parseColor("#5496DB"));
        }
        return menuName;
    }

    public TextView setUnSelected() {
        if (menuName != null) {
            if (menuName.getText().toString().equals("Thiết lập người dùng") ||
                    menuName.getText().toString().equals("Thông tin ứng dụng")) {
                menuName.setTextColor(Color.parseColor("#959595"));
            } else {
                menuName.setTextColor(Color.BLACK);
            }

        }
        return menuName;
    }

    void expandCurrentandCloseOtherNode(TreeNode node) {
        for (TreeNode n : node.getParent().getChildren()) {
            if (n == node) {
                if (n.isExpanded()) {
                    tView.collapseNode(n);
                    // unhightline and collapse child node add start
                    if (n.getChildren().size() != 0) {
                        for (TreeNode child : node.getChildren()) {
                            TreeMenu adapterChild = (TreeMenu) child.getViewHolder();
                            adapterChild.setUnSelected();
                            if (child.getChildren().size() != 0) {
                                tView.collapseNode(child);
                            }
                        }
                    }
                    // unhightline and collapse child node add end
                } else {
                    tView.expandNode(n);
                }

            } else {
                if (n.getChildren().size() != 0) {
                    for (TreeNode c : n.getChildren()) {
                        if (c.isExpanded()) {
                            tView.collapseNode(c);
                        }
                    }
                }
                tView.collapseNode(n);
            }

        }
    }

    @Override
    public void toggleSelectionMode(boolean editModeEnabled) {
        if (editModeEnabled) {
            menuName.setTextColor(Color.parseColor("#ff0"));
        }
        super.toggleSelectionMode(editModeEnabled);
    }

    @Override
    public void toggle(boolean active) {
        Drawable imgID = menuRight.getDrawable();
        Log.d("VinhCN: ", imgID + "");
        if (imgID != null) {
            menuRight.setImageResource(active ? R.drawable.down_arrow_x1 : R.drawable.right_arrow_x1);
        } else {
            menuRight.setImageResource(active ? 0 : 0);
        }
        super.toggle(active);
    }

    @Override
    public void onClick(View v) {
//        int position = Integer.parseInt(view.getTag().toString());
        switch (v.getId()) {
            case R.id.change_position:
            case R.id.change_profile:
            case R.id.change_pass:
            case R.id.log_out:
                ((OfficalActivity) context).SettingCountClick(v);
                break;
        }
    }

    public static class MenuTreeItem {
        String MenuName;
        public int MenuIcon;
        public int MenuPriority;
        public String MenuCode;
        public int HeaderCount;
        public boolean CheckColor;
        public int heightLine;
        public boolean ShowArrowRight;
        public boolean changeArrowRightToLeft;
        public String textColor;
        public boolean boldText;

        public MenuTreeItem(String menuName, int menuIcon, int menuPriority, String menuCode, int headerCount, boolean checkColor, int heightLine, boolean showArrowRight, boolean changeArrowRightToLeft, String textcolor, boolean boldtext) {
            MenuName = menuName;
            MenuIcon = menuIcon;
            MenuPriority = menuPriority;
            MenuCode = menuCode;
            HeaderCount = headerCount;
            CheckColor = checkColor;
            this.heightLine = heightLine;
            ShowArrowRight = showArrowRight;
            this.changeArrowRightToLeft = changeArrowRightToLeft;
            this.textColor = textcolor;
            this.boldText = boldtext;
        }

        public boolean isBoldText() {
            return boldText;
        }

        public void setBoldText(boolean boldText) {
            this.boldText = boldText;
        }

        public String getTextColor() {
            return textColor;
        }

        public void setTextColor(String textColor) {
            this.textColor = textColor;
        }

        public String getMenuName() {
            SumManager manager = new SumManager();
            Spanned textx = Html.fromHtml(MenuName);
            String xxx = textx.toString();
            String number = manager.getOnlyNumerics(xxx);
            return xxx.replace(number, "");
        }

        public void setMenuName(String menuName) {
            MenuName = menuName;
        }

        public int getMenuIcon() {
            return MenuIcon;
        }

        public void setMenuIcon(int menuIcon) {
            MenuIcon = menuIcon;
        }

        public int getMenuPriority() {
            return MenuPriority;
        }

        public void setMenuPriority(int menuPriority) {
            MenuPriority = menuPriority;
        }

        public String getMenuCode() {
            return MenuCode;
        }

        public void setMenuCode(String menuCode) {
            MenuCode = menuCode;
        }

        public int getHeaderCount() {
            return HeaderCount;
        }

        public void setHeaderCount(int headerCount) {
            HeaderCount = headerCount;
        }

        public boolean isCheckColor() {
            return CheckColor;
        }

        public void setCheckColor(boolean checkColor) {
            CheckColor = checkColor;
        }

        public int getHeightLine() {
            return heightLine;
        }

        public void setHeightLine(int heightLine) {
            this.heightLine = heightLine;
        }

        public boolean isShowArrowRight() {
            return ShowArrowRight;
        }

        public void setShowArrowRight(boolean showArrowRight) {
            ShowArrowRight = showArrowRight;
        }

        public boolean isChangeArrowRightToLeft() {
            return changeArrowRightToLeft;
        }

        public void setChangeArrowRightToLeft(boolean changeArrowRightToLeft) {
            this.changeArrowRightToLeft = changeArrowRightToLeft;
        }
    }

}
