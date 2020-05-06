if(!empty($_REQUEST["id"])) {
  try {
    $stmt = $db->prepare("DELETE FROM person WHERE id_person = :id");
    $stmt->bindValue(":id", $_REQUEST["id"]);
    $stmt->execute();
    header("Location: index.php");
  } catch (Exception $e) {
    die($e->getMessage());
  }
} else {
  die("Nezadano ID");
}