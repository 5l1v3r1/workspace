<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form id="form1" name="form1" method="get" action="list.php" target="list">
  <table width="960" border="0" align="center" cellpadding="0" cellspacing="0">

    <tr height="15"></tr>
    <tr>

      <td width="235" height="24" valign="buttom">
        <select name="key" id="key">
          <option value="name">수강생</option>
          <option value="phone">전화번호</option>
          <option value="subject">수강과목</option>
        </select>
        <input name="search" type="text" id="textfield" size="10" maxlength="13" />
        <input name="submit" type="submit" value="검색" style="background-color:#b6f06a; border:0;width:43px;height:24px;"/>
      </td>

      <td width="75"> &nbsp </td>          

      <td width="630" height="24" align="center">
          ------------------------------------ 출결현황 ------------------------------------
      </td>

    </tr>

    <tr height="15"></tr>

    <tr>

      <td height="480" align="center" colspan="2">
          <iframe width="300" height="480" scrolling="Yes" name="list" src="list.php">
              이 웹브라우저는 iframe을 지원하지 않습니다.
          </iframe>
      </td>

      <td height="480" align="center">
          <iframe width="630" height="480" scrolling="Yes" name="log" src="log.php">
              이 웹브라우저는 iframe을 지원하지 않습니다.
          </iframe>
      </td>

    </tr>

  </table>
</form>
</body>
</html>
