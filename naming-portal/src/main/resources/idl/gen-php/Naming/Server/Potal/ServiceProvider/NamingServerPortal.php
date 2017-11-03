<?php
namespace Naming\Server\Potal\ServiceProvider;
/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
use Thrift\Base\TBase;
use Thrift\Type\TType;
use Thrift\Type\TMessageType;
use Thrift\Exception\TException;
use Thrift\Exception\TProtocolException;
use Thrift\Protocol\TProtocol;
use Thrift\Protocol\TBinaryProtocolAccelerated;
use Thrift\Exception\TApplicationException;


interface NamingServerPortalIf {
  /**
   * @param string $protocolType
   * @param string $ns
   * @param string $serviceName
   * @param string $host
   * @param int $port
   * @return bool
   */
  public function doRegister($protocolType, $ns, $serviceName, $host, $port);
}

class NamingServerPortalClient implements \Naming\Server\Potal\ServiceProvider\NamingServerPortalIf {
  protected $input_ = null;
  protected $output_ = null;

  protected $seqid_ = 0;

  public function __construct($input, $output=null) {
    $this->input_ = $input;
    $this->output_ = $output ? $output : $input;
  }

  public function doRegister($protocolType, $ns, $serviceName, $host, $port)
  {
    $this->send_doRegister($protocolType, $ns, $serviceName, $host, $port);
    return $this->recv_doRegister();
  }

  public function send_doRegister($protocolType, $ns, $serviceName, $host, $port)
  {
    $args = new \Naming\Server\Potal\ServiceProvider\NamingServerPortal_doRegister_args();
    $args->protocolType = $protocolType;
    $args->ns = $ns;
    $args->serviceName = $serviceName;
    $args->host = $host;
    $args->port = $port;
    $bin_accel = ($this->output_ instanceof TBinaryProtocolAccelerated) && function_exists('thrift_protocol_write_binary');
    if ($bin_accel)
    {
      thrift_protocol_write_binary($this->output_, 'doRegister', TMessageType::CALL, $args, $this->seqid_, $this->output_->isStrictWrite());
    }
    else
    {
      $this->output_->writeMessageBegin('doRegister', TMessageType::CALL, $this->seqid_);
      $args->write($this->output_);
      $this->output_->writeMessageEnd();
      $this->output_->getTransport()->flush();
    }
  }

  public function recv_doRegister()
  {
    $bin_accel = ($this->input_ instanceof TBinaryProtocolAccelerated) && function_exists('thrift_protocol_read_binary');
    if ($bin_accel) $result = thrift_protocol_read_binary($this->input_, '\Naming\Server\Potal\ServiceProvider\NamingServerPortal_doRegister_result', $this->input_->isStrictRead());
    else
    {
      $rseqid = 0;
      $fname = null;
      $mtype = 0;

      $this->input_->readMessageBegin($fname, $mtype, $rseqid);
      if ($mtype == TMessageType::EXCEPTION) {
        $x = new TApplicationException();
        $x->read($this->input_);
        $this->input_->readMessageEnd();
        throw $x;
      }
      $result = new \Naming\Server\Potal\ServiceProvider\NamingServerPortal_doRegister_result();
      $result->read($this->input_);
      $this->input_->readMessageEnd();
    }
    if ($result->success !== null) {
      return $result->success;
    }
    throw new \Exception("doRegister failed: unknown result");
  }

}

// HELPER FUNCTIONS AND STRUCTURES

class NamingServerPortal_doRegister_args {
  static $_TSPEC;

  /**
   * @var string
   */
  public $protocolType = null;
  /**
   * @var string
   */
  public $ns = null;
  /**
   * @var string
   */
  public $serviceName = null;
  /**
   * @var string
   */
  public $host = null;
  /**
   * @var int
   */
  public $port = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        -1 => array(
          'var' => 'protocolType',
          'type' => TType::STRING,
          ),
        -2 => array(
          'var' => 'ns',
          'type' => TType::STRING,
          ),
        -3 => array(
          'var' => 'serviceName',
          'type' => TType::STRING,
          ),
        -4 => array(
          'var' => 'host',
          'type' => TType::STRING,
          ),
        -5 => array(
          'var' => 'port',
          'type' => TType::I32,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['protocolType'])) {
        $this->protocolType = $vals['protocolType'];
      }
      if (isset($vals['ns'])) {
        $this->ns = $vals['ns'];
      }
      if (isset($vals['serviceName'])) {
        $this->serviceName = $vals['serviceName'];
      }
      if (isset($vals['host'])) {
        $this->host = $vals['host'];
      }
      if (isset($vals['port'])) {
        $this->port = $vals['port'];
      }
    }
  }

  public function getName() {
    return 'NamingServerPortal_doRegister_args';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case -1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->protocolType);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case -2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->ns);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case -3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->serviceName);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case -4:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->host);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case -5:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->port);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('NamingServerPortal_doRegister_args');
    if ($this->port !== null) {
      $xfer += $output->writeFieldBegin('port', TType::I32, -5);
      $xfer += $output->writeI32($this->port);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->host !== null) {
      $xfer += $output->writeFieldBegin('host', TType::STRING, -4);
      $xfer += $output->writeString($this->host);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->serviceName !== null) {
      $xfer += $output->writeFieldBegin('serviceName', TType::STRING, -3);
      $xfer += $output->writeString($this->serviceName);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->ns !== null) {
      $xfer += $output->writeFieldBegin('ns', TType::STRING, -2);
      $xfer += $output->writeString($this->ns);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->protocolType !== null) {
      $xfer += $output->writeFieldBegin('protocolType', TType::STRING, -1);
      $xfer += $output->writeString($this->protocolType);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class NamingServerPortal_doRegister_result {
  static $_TSPEC;

  /**
   * @var bool
   */
  public $success = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        0 => array(
          'var' => 'success',
          'type' => TType::BOOL,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['success'])) {
        $this->success = $vals['success'];
      }
    }
  }

  public function getName() {
    return 'NamingServerPortal_doRegister_result';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 0:
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->success);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('NamingServerPortal_doRegister_result');
    if ($this->success !== null) {
      $xfer += $output->writeFieldBegin('success', TType::BOOL, 0);
      $xfer += $output->writeBool($this->success);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}


