package com.hb.scms.main.freemarker;

public class CreateAll {
    public static void main(String[] args) {
        String table = "sys_busi_ty";
        try {
             CreateController.main(new String[]{table});
            CreateMapper.main(new String[]{table});
           CreateModel.main(new String[]{table});
          CreateModelDto.main(new String[]{table});
         /*    CreateVForm.main(new String[]{table});
            CreateVList.main(new String[]{table});
           CreateVListJs.main(new String[]{table});*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
