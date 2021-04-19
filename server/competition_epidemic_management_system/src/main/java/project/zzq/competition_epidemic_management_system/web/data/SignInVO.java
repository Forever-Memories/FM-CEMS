/**
 * @(#)SignInVO.java, 4月 19, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package project.zzq.competition_epidemic_management_system.web.data;

import lombok.Data;

/**
 * @author zhuzheqing
 */
@Data
public class SignInVO {
    private String phoneNumber;
    private String password;
}